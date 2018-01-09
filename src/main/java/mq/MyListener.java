package mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Rafaxu
 */

public class MyListener implements javax.jms.MessageListener {

    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                final String messageText = ((TextMessage) message).getText();
                System.out.println(messageText);
            }
            catch (JMSException ex) {
                throw new RuntimeException(ex);
            }
        }
        else {
            throw new IllegalArgumentException("wrong type");
        }
    }
}
