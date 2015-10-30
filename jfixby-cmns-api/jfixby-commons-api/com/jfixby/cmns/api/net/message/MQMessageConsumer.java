package com.jfixby.cmns.api.net.message;

public interface MQMessageConsumer {

	Message tryToReceiveMessage(long period) throws MQTransportException;

	void close() throws MQTransportException;

}
