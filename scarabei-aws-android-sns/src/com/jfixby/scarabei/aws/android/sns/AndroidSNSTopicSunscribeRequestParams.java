
package com.jfixby.scarabei.aws.android.sns;

import com.jfixby.scarabei.aws.api.sns.SNSTopicSunscribeRequestParams;

public class AndroidSNSTopicSunscribeRequestParams implements SNSTopicSunscribeRequestParams {

	private String endPoint;
	private String protocol;
	private String topic;
	private String region;

	@Override
	public void setRegion (final String string) {
		this.region = string;
	}

	@Override
	public String getRegion () {
		return this.region;
	}

	@Override
	public void setTopicARN (final String string) {
		this.topic = string;
	}

	@Override
	public String getTopicARN () {
		return this.topic;
	}

	@Override
	public void setProtocol (final String string) {
		this.protocol = string;
	}

	@Override
	public String getProtocol () {
		return this.protocol;
	}

	@Override
	public String getEndPoint () {
		return this.endPoint;
	}

	@Override
	public String setEndPoint (final String endPoint) {
		return endPoint;
	}

}
