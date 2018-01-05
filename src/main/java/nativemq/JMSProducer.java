package nativemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSProducer {

    // assume using the default username and password for
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKERURL = "tcp://34.230.92.231:61616";
    private static final int MSGCount = 10;

    public static void main(String[] args) {

        // define the variables
        Connection connection = null;

        // Get the connection factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMSProducer.USERNAME, JMSProducer.PASSWORD, JMSProducer.BROKERURL);

        try {
            // create the connection
            connection = connectionFactory.createConnection();
            // start the connection
            connection.start();
            // create session
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // create the destination
            Destination destination = session.createQueue("FirstQueue1");
            // create the message producer
            MessageProducer messageProducer = session.createProducer(destination);
            // send messages
            sendMessage(session, messageProducer);
            // commit the sending
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
        for (int i = 0; i < JMSProducer.MSGCount; i++) {
            TextMessage message = session.createTextMessage("ActiveMQ Message: " + i);
            System.out.println("Message sent: " + i);
            messageProducer.send(message);
        }
    }
}
