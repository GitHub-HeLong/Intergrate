package com.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component(value = "msgListener")
public class MQListenerContainer implements MessageListener {
	private Logger logger = Logger.getLogger(MQListenerContainer.class);

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				String text = textMessage.getText();
				logger.info("Listener received text : " + text);
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}
	}
}
