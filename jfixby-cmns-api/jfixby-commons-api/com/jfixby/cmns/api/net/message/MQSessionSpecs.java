package com.jfixby.cmns.api.net.message;

public interface MQSessionSpecs {

	boolean getTransacted();

	int getAcknowledgeMode();

}
