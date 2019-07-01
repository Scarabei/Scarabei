
package com.jfixby.scarabei.amazon.aws.sqs;

import com.jfixby.scarabei.aws.api.sqs.SQSSendMessageParams;

public class RedSQSSendMessageParams implements SQSSendMessageParams {

	private String queuURL;
	private String body;

	@Override
	public void setQueueURL (final String queuURL) {
		this.queuURL = queuURL;
	}

	@Override
	public String getQueueURL () {
		return this.queuURL;
	}

	@Override
	public String getBody () {
		return this.body;
	}

	@Override
	public void setBody (final String body) {
		this.body = body;
	}

}
