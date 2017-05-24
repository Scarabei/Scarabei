
package com.jfixby.scarabei.aws.desktop.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageParams;

public class DesktopSQSReceiveMessageRequestParams implements SQSReceiveMessageParams {

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
