package com.jfixby.cmns.api.net.message;

public interface Message {

	// void setHeader(String header) throws MQTransportException;

	void print() throws MQTransportException;

	void setStringProperty(String name, String value)
			throws MQTransportException;

	String getStringProperty(String paremeter_name) throws MQTransportException;

	 
	//
	// String getHeader() throws MQTransportException;
	//
	// String getParameter(String paremeter_name) throws MQTransportException;
	//
	// void setParameter(String paremeter_name, String paremeter_value)
	// throws MQTransportException;

}
