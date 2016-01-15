package com.jfixby.cmns.api.net.message;

public interface MQMessageConsumerSpecs {

	void setIncommingMailbox(MQDestination mailbox);

	MQDestination getIncommingMailbox();

}
