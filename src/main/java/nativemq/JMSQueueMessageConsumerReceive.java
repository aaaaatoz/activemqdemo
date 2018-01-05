package nativemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSQueueMessageConsumerReceive {

    // assume using the default username and password for
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKERURL = "tcp://34.230.92.231:61616";
    private static final int MSGCount = 10;

    public static void main(String[] args) {

        // define the variables
        Connection connection = null;

        // Get the connection factory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMSQueueMessageConsumerReceive.USERNAME, JMSQueueMessageConsumerReceive.PASSWORD, JMSQueueMessageConsumerReceive.BROKERURL);

        try {
            // create the connection
            connection = connectionFactory.createConnection();
            // start the connection
            connection.start();
            // create session
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // create the destination
            Destination destination = session.createQueue("FirstQueue");
            // create consumer
            MessageConsumer messageConsumer = session.createConsumer(destination);
            // keep receiving message from consumer
            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
                if (textMessage != null) {
                    System.out.println("Message from ActiveMQ: " + textMessage.getText());
                } else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
