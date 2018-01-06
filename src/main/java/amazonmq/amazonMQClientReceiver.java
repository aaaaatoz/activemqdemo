package amazonmq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class amazonMQClientReceiver {

    public static void main(String[] args) throws Exception {
        // Create a connection factory.
        String failoverConnection = "failover:(ssl://b-2353da1e-694c-416e-bd90-f7b74b92f5a6-1.mq.us-west-2.amazonaws.com:61617,ssl://b-2353da1e-694c-416e-bd90-f7b74b92f5a6-2.mq.us-west-2.amazonaws.com:61617)";
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(failoverConnection);

        // Specify the username and password.
        connectionFactory.setUserName("root");
        connectionFactory.setPassword("fake");


        // Establish a connection for the consumer.
        Connection consumerConnection = connectionFactory.createConnection();
        consumerConnection.start();

        // Create a session.
        Session consumerSession = consumerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a queue named "MyQueue".
        Destination consumerDestination = consumerSession.createQueue("NewQueue");

        // Create a message consumer from the session to the queue.
        MessageConsumer consumer = consumerSession.createConsumer(consumerDestination);

        // Begin to wait for messages.

        for (int i = 0; i <= 10000; i++){
            Message consumerMessage = consumer.receive(1000);
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("Messages receiving stopped at:" + dateFormat.format(date)); //2016/11/16 12:08:43

    }
}
