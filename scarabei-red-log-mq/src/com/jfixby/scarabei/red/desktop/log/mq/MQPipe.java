
package com.jfixby.scarabei.red.desktop.log.mq;

import java.util.ArrayList;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.log.MESSAGE_MARKER;

public class MQPipe {

	final ArrayList<String> log_messages = new ArrayList<String>();
	final int MAX = 500;
	private final String LOG_PROPERTY_TAG = "LOG.MESSAGE";

	boolean inited = false;
	private String url;
	private String BOX;
	private final Runnable runner = new Runnable() {

		@Override
		public void run () {
			MQPipe.this.go();
		}
	};

	boolean go = true;

	void go () {
		this.connect();
		while (this.go) {
			this.send();
		}
		this.disconnect();
	}

	private void send () {
		if (this.log_messages.size() > 0) {
			final String element_to_send = this.log_messages.remove(0);
			try {
				final Message msg = this.session.createMessage();
				msg.setStringProperty(this.LOG_PROPERTY_TAG, element_to_send);
				this.producer.send(msg);
			} catch (final JMSException e) {
				e.printStackTrace();
				this.sleep(5000);
			}
		}
	}

	private void sleep (final long period) {
		try {
			Thread.sleep(period);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Connection connection;
	private ActiveMQSession session;
	private MessageProducer producer;

	ActiveMQConnectionFactory connectionFactory;
	private long MESSAGE_EXPIRE_PERIOD;

	void connect () {
		try {
			// Create a ConnectionFactory
			this.connectionFactory = new ActiveMQConnectionFactory(this.url);
			L.d("MQ is up: " + this.connectionFactory);
			// Create a Connection
			this.connection = this.connectionFactory.createConnection();
			this.connection.start();

			// Create a Session
			this.session = (ActiveMQSession)this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			final Destination destination = this.session.createQueue(this.BOX);

			// Create a MessageProducer from the Session to the Topic or
			// Queue
			this.producer = this.session.createProducer(destination);
			L.d("Transmitting to " + destination);
			this.producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			this.producer.setTimeToLive(this.MESSAGE_EXPIRE_PERIOD);

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}

	void disconnect () {
		try {
			this.session.close();
			this.connection.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void init (final String url, final String logger_box, final String mq_server_file_password,
		final long MESSAGE_EXPIRE_PERIOD) {
		if (this.inited) {
			return;
		}
		this.MESSAGE_EXPIRE_PERIOD = MESSAGE_EXPIRE_PERIOD;
		this.inited = true;
		this.BOX = logger_box;
		this.url = url;

		final String replacement = mq_server_file_password.substring(0, 5) + "******************";

		L.d("Starting :" + url.replaceAll(mq_server_file_password, replacement));

	}

	public void start () {
		if (this.url == null) {
			Err.reportError("ActiveMQLogger is not initialized.");
		}
		final Thread t = new Thread(this.runner);
		t.start();
	}

	private void log (final Object obj) {
		System.out.println("pipe: " + obj);
		final String msg = obj + "";
		this.log_messages.add(new Date() + "\n" + msg);
		if (this.log_messages.size() > this.MAX) {
			this.log_messages.remove(0);
			this.log_messages.remove(0);
			this.log_messages.remove(0);
		}
	}

	public void logLine (final MESSAGE_MARKER marker, final Object string) {
		this.log(string);
	}

	public void logAppend (final MESSAGE_MARKER marker, final Object string) {
		this.log(string);
	}

	public void logLine (final MESSAGE_MARKER marker) {
		this.log("");
	}

	public void logAppend (final MESSAGE_MARKER marker) {
		this.log("");
	}
}
