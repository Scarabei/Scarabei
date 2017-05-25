
package com.jfixby.scarabei.aws.android.sns;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.aws.api.sns.SNSTopicSunscribeRequest;
import com.jfixby.scarabei.aws.api.sns.SNSTopicSunscribeRequestParams;

public class AndroidSNSTopicSunscribeRequest implements SNSTopicSunscribeRequest {

	private final String protocol;
	private final String topic;
	private final String region;
	private final String endPoint;

	public AndroidSNSTopicSunscribeRequest (final SNSTopicSunscribeRequestParams params, final AndroidSNSClient redSNSClient) {
		this.protocol = params.getProtocol();
		this.region = params.getRegion();
		this.topic = params.getTopicARN();
		this.endPoint = params.getEndPoint();

		final AmazonSNSClient sns = redSNSClient.getSNSClient();

		final SubscribeRequest subRequest = new SubscribeRequest(this.topic, this.protocol, this.endPoint);
		sns.subscribe(subRequest);
		// get request id for SubscribeRequest from SNS metadata
		L.d("SubscribeRequest - " + sns.getCachedResponseMetadata(subRequest));
// System.out.println("Check your email and confirm subscription.");

	}

}
