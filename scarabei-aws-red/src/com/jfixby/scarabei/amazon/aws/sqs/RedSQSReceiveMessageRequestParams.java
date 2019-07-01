
package com.jfixby.scarabei.amazon.aws.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageParams;

public class RedSQSReceiveMessageRequestParams implements SQSReceiveMessageParams {

	private String url;

	@Override
	public String getQueueURL () {
		return this.url;
	}

	@Override
	public void setQueueURL (final String url) {
		this.url = url;
	}

}
