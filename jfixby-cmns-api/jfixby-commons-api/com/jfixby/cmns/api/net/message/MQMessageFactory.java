package com.jfixby.cmns.api.net.message;

public interface MQMessageFactory {

	MQConnection newConnection(MQConnectionSpecs specs) throws MQTransportException;

	MQConnectionSpecs newConnectionSpecs();

}
