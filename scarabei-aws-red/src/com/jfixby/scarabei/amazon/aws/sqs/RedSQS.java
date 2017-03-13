
package com.jfixby.scarabei.amazon.aws.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQS;
import com.jfixby.scarabei.aws.api.sqs.SQSClienSpecs;
import com.jfixby.scarabei.aws.api.sqs.SQSClient;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequestParams;

public class RedSQS implements SQS {

	@Override
	public SQSClienSpecs newSQSClienSpecs () {
		return new RedSQSClienSpecs();
	}

	@Override
	public SQSClient newClient (final SQSClienSpecs specs) {
		return new RedSQSClient(specs);
	}

	@Override
	public SQSReceiveMessageRequestParams newReceiveMessageRequestParams () {
		return new RedSQSReceiveMessageRequestParams();
	}

	@Override
	public SQSReceiveMessageRequest newReceiveMessageRequest (final SQSReceiveMessageRequestParams params) {
		return new RedSQSReceiveMessageRequest(params);
	}

}
