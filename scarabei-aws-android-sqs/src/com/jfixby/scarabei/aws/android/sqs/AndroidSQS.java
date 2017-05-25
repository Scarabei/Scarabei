
package com.jfixby.scarabei.aws.android.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSComponent;
import com.jfixby.scarabei.aws.api.sqs.SQSClienSpecs;
import com.jfixby.scarabei.aws.api.sqs.SQSClient;
import com.jfixby.scarabei.aws.api.sqs.SQSCreateQueueParams;
import com.jfixby.scarabei.aws.api.sqs.SQSDeleteMessageParams;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageParams;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSSendMessageParams;

public class AndroidSQS implements SQSComponent {

	@Override
	public SQSClienSpecs newSQSClienSpecs () {
		return new AndroidSQSClienSpecs();
	}

	@Override
	public SQSClient newClient (final SQSClienSpecs specs) {
		return new AndroidSQSClient(specs);
	}

	@Override
	public SQSReceiveMessageParams newReceiveMessageParams () {
		return new AndroidSQSReceiveMessageRequestParams();
	}

	@Override
	public SQSReceiveMessageRequest newReceiveMessageRequest (final SQSReceiveMessageParams params) {
		return new AndroidSQSReceiveMessageRequest(params);
	}

	@Override
	public SQSCreateQueueParams newCreateQueueParams () {
		return new AndroidSQSCreateQueueParams();
	}

	@Override
	public SQSSendMessageParams newSendMessageParams () {
		return new AndroidSQSSendMessageParams();
	}

	@Override
	public SQSDeleteMessageParams newDeleteMessageParams () {
		return new AndroidSQSDeleteMessageParams();
	}

}
