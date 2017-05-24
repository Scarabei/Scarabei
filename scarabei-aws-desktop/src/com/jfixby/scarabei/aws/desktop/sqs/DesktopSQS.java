
package com.jfixby.scarabei.aws.desktop.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSComponent;
import com.jfixby.scarabei.aws.api.sqs.SQSClienSpecs;
import com.jfixby.scarabei.aws.api.sqs.SQSClient;
import com.jfixby.scarabei.aws.api.sqs.SQSCreateQueueParams;
import com.jfixby.scarabei.aws.api.sqs.SQSDeleteMessageParams;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageParams;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSSendMessageParams;

public class DesktopSQS implements SQSComponent {

	@Override
	public SQSClienSpecs newSQSClienSpecs () {
		return new DesktopSQSClienSpecs();
	}

	@Override
	public SQSClient newClient (final SQSClienSpecs specs) {
		return new DesktopSQSClient(specs);
	}

	@Override
	public SQSReceiveMessageParams newReceiveMessageParams () {
		return new DesktopSQSReceiveMessageRequestParams();
	}

	@Override
	public SQSReceiveMessageRequest newReceiveMessageRequest (final SQSReceiveMessageParams params) {
		return new DesktopSQSReceiveMessageRequest(params);
	}

	@Override
	public SQSCreateQueueParams newCreateQueueParams () {
		return new DesktopSQSCreateQueueParams();
	}

	@Override
	public SQSSendMessageParams newSendMessageParams () {
		return new DesktopSQSSendMessageParams();
	}

	@Override
	public SQSDeleteMessageParams newDeleteMessageParams () {
		return new DesktopSQSDeleteMessageParams();
	}

}
