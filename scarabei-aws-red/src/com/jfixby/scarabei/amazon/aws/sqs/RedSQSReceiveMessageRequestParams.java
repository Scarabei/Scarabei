
package com.jfixby.scarabei.amazon.aws.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequestParams;

public class RedSQSReceiveMessageRequestParams implements SQSReceiveMessageRequestParams {

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
