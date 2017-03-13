
package com.jfixby.scarabei.aws.api.sqs;

public interface SQSClient {
	SQSReceiveMessageRequestResult receive (SQSReceiveMessageRequest request);
}
