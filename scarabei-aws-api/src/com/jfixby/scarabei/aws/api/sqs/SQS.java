
package com.jfixby.scarabei.aws.api.sqs;

public interface SQS {

	SQSClienSpecs newSQSClienSpecs ();

	SQSClient newClient (SQSClienSpecs specs);

	SQSReceiveMessageRequestParams newReceiveMessageRequestParams ();

	SQSReceiveMessageRequest newReceiveMessageRequest (SQSReceiveMessageRequestParams params);

}
