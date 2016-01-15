package com.jfixby.cmns.api.net.message;

public interface MQMessageProducer {

	void send(Message message) throws MQTransportException;

	void close() throws MQTransportException;

}
