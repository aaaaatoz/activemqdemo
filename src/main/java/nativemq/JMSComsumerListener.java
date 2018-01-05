package nativemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSComsumerListener {

    // assume using the default username and password for
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKERURL = "tcp://34.230.92.231:61616";
    private static final int MSGCount = 10;

    public static void main(String[] args) {

        // define the variables
        Connection connection = null;

        // Get the connection factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMSComsumerListener.USERNAME, JMSComsumerListener.PASSWORD, JMSComsumerListener.BROKERURL);

        try {
            // create the connection
            connection = connectionFactory.createConnection();
            // start the connection
            connection.start();
            // create session
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // create the destination
            Destination destination = session.createQueue("FirstQueue1");
            // create consumer
            MessageConsumer messageConsumer = session.createConsumer(destination);
            // set listener for consumer
            messageConsumer.setMessageListener(new Listener());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

class Listener implements MessageListener{

    public void onMessage(Message message) {
        try {
            System.out.println("Receive Message from ActiveMQ: "+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
