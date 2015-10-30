package com.jfixby.red.desktop.log.mq;

import java.util.Date;
import java.util.Vector;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

import com.jfixby.cmns.api.log.L;

public class MQPipe {

	final Vector<String> log_messages = new Vector<String>();
	final int MAX = 500;
	private final String LOG_PROPERTY_TAG = "LOG.MESSAGE";

	public void d() {
		d("");
	}

	public void d(Object obj) {
		System.out.println("pipe: " + obj);
		String msg = obj + "";
		log_messages.add(new Date() + "\n" + msg);
		if (log_messages.size() > MAX) {
			log_messages.remove(0);
			log_messages.remove(0);
			log_messages.remove(0);
		}

	}

	boolean inited = false;
	private String url;
	private String BOX;
	private Runnable runner = new Runnable() {

		@Override
		public void run() {
			go();
		}
	};

	boolean go = true;

	void go() {
		connect();
		while (go) {
			send();
		}
		disconnect();
	}

	private void send() {
		if (log_messages.size() > 0) {
			String element_to_send = log_messages.remove(0);
			try {
				Message msg = session.createMessage();
				msg.setStringProperty(LOG_PROPERTY_TAG, element_to_send);
				this.producer.send(msg);
			} catch (JMSException e) {
				e.printStackTrace();
				sleep(5000);
			}
		}
	}

	private void sleep(long period) {
		try {
			Thread.sleep(period);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Connection connection;
	private ActiveMQSession session;
	private MessageProducer producer;

	ActiveMQConnectionFactory connectionFactory;
	private long MESSAGE_EXPIRE_PERIOD;

	void connect() {
		try {
			// Create a ConnectionFactory
			connectionFactory = new ActiveMQConnectionFactory(url);
			L.d("MQ is up: " + connectionFactory);
			// Create a Connection
			connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			session = (ActiveMQSession) connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue(BOX);

			// Create a MessageProducer from the Session to the Topic or
			// Queue
			producer = session.createProducer(destination);
			L.d("Transmitting to " + destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			producer.setTimeToLive(MESSAGE_EXPIRE_PERIOD);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void disconnect() {
		try {
			session.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void init(String url, String logger_box,
			String mq_server_file_password, long MESSAGE_EXPIRE_PERIOD) {
		if (inited) {
			return;
		}
		this.MESSAGE_EXPIRE_PERIOD = MESSAGE_EXPIRE_PERIOD;
		inited = true;
		BOX = logger_box;
		this.url = url;

		String replacement = mq_server_file_password.substring(0, 5)
				+ "******************";

		L.d("Starting :" + url.replaceAll(mq_server_file_password, replacement));

	}

	public void e() {
		d();
	}

	public void e(Object string) {
		d(string);
	}

	public void start() {
		if (url == null) {
			throw new Error("ActiveMQLogger is not initialized.");
		}
		Thread t = new Thread(runner);
		t.start();
	}
}
