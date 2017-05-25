
package com.jfixby.scarabei.aws.android.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageParams;

public class AndroidSQSReceiveMessageRequestParams implements SQSReceiveMessageParams {

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
