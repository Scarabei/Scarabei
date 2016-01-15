package com.jfixby.cmns.api.net.message;

public interface MQSession {

	MQQueueDestination createQueue(String name);

	MQTopicDestination createTopic(String name);

	MQMessageConsumerSpecs newMessageConsumerSpecs();

	MQMessageConsumer newMessageConsumer(MQMessageConsumerSpecs receiver_specs) throws MQTransportException;

	MQMessageProducerSpecs newMessageProducerSpecs();

	MQMessageProducer newMessageProducer(MQMessageProducerSpecs sender_specs) throws MQTransportException;

	Message newMessage() throws MQTransportException;

}
