
package com.jfixby.scarabei.amazon.aws.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSClienSpecs;
import com.jfixby.scarabei.aws.api.sqs.SQSClient;
import com.jfixby.scarabei.aws.api.sqs.SQSComponent;
import com.jfixby.scarabei.aws.api.sqs.SQSCreateQueueParams;
import com.jfixby.scarabei.aws.api.sqs.SQSDeleteMessageParams;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageParams;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSSendMessageParams;

public class RedSQS implements SQSComponent {

	@Override
	public SQSClienSpecs newSQSClienSpecs () {
		return new RedSQSClienSpecs();
	}

	@Override
	public SQSClient newClient (final SQSClienSpecs specs) {
		return new RedSQSClient(specs);
	}

	@Override
	public SQSReceiveMessageParams newReceiveMessageParams () {
		return new RedSQSReceiveMessageRequestParams();
	}

	@Override
	public SQSReceiveMessageRequest newReceiveMessageRequest (final SQSReceiveMessageParams params) {
		return new RedSQSReceiveMessageRequest(params);
	}

	@Override
	public SQSCreateQueueParams newCreateQueueParams () {
		return new RedSQSCreateQueueParams();
	}

	@Override
	public SQSSendMessageParams newSendMessageParams () {
		return new RedSQSSendMessageParams();
	}

	@Override
	public SQSDeleteMessageParams newDeleteMessageParams () {
		return new RedSQSDeleteMessageParams();
	}

}
