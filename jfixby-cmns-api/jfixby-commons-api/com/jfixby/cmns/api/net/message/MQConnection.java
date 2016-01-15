package com.jfixby.cmns.api.net.message;

public interface MQConnection {

	public void start() throws MQTransportException;

	public MQSession newSession(MQSessionSpecs session_specs) throws MQTransportException;

	MQSessionSpecs newSessionSpecs();

	public String getClientID();

}
