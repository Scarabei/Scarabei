
package com.jfixby.scarabei.aws.api.sqs;

public interface SQSComponent {

	SQSClienSpecs newSQSClienSpecs ();

	SQSClient newClient (SQSClienSpecs specs);

	SQSReceiveMessageParams newReceiveMessageParams ();

	SQSReceiveMessageRequest newReceiveMessageRequest (SQSReceiveMessageParams params);

	SQSCreateQueueParams newCreateQueueParams ();

	SQSSendMessageParams newSendMessageParams ();

	SQSDeleteMessageParams newDeleteMessageParams ();

}
