package mq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by Rafa XU
 */
public class MessageProducer implements Runnable{
    private JmsTemplate jmsTemplate;

    public MessageProducer(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    public void run(){
        while(true) {
            try {
                jmsTemplate.send("inputQueue", new MessageCreator() {
                    public Message createMessage(Session session) throws JMSException {
                        long delay = 300 * 1000;//5 mins

                        TextMessage textMessage = session.createTextMessage("Hello World");

                        return textMessage;
                    }
                });
            } catch (Exception ex) {
                System.err.println("Error in running message producer thread:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
