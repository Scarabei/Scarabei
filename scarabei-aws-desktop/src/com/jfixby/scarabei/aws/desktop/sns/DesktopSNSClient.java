
package com.jfixby.scarabei.aws.desktop.sns;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.sns.SNSClient;
import com.jfixby.scarabei.aws.api.sns.SNSClientSpecs;
import com.jfixby.scarabei.aws.api.sns.SNSTopicSunscribeRequest;
import com.jfixby.scarabei.aws.api.sns.SNSTopicSunscribeRequestParams;

public class DesktopSNSClient implements SNSClient {

	private AmazonSNSClient awsSnsClient;

	public DesktopSNSClient (final SNSClientSpecs params) {
		final AWSCredentialsProvider keys = params.getAWSCredentialsProvider();
		final String regionName = keys.getRegionName();
		final com.amazonaws.auth.AWSCredentialsProvider awsKeys = new com.amazonaws.auth.AWSCredentialsProvider() {

			@Override
			public AWSCredentials getCredentials () {
				return new AWSCredentials() {

					@Override
					public String getAWSAccessKeyId () {
						return keys.getAccessKeyID();
					}

					@Override
					public String getAWSSecretKey () {
						return keys.getSecretKeyID();
					}
				};
			}

			@Override
			public void refresh () {
			}
		};

		// final create a new final SNS client and final set endpoint
		this.awsSnsClient = new AmazonSNSClient(awsKeys);
		this.awsSnsClient.setRegion(com.amazonaws.regions.Region.getRegion(com.amazonaws.regions.Regions.fromName(regionName)));

	}

	@Override
	public SNSTopicSunscribeRequestParams newSubscribeParams () {
		return new DesktopSNSTopicSunscribeRequestParams();
	}

	@Override
	public SNSTopicSunscribeRequest subscribe (final SNSTopicSunscribeRequestParams params) {
		return new DesktopSNSTopicSunscribeRequest(params, this);
	}

	AmazonSNSClient getSNSClient () {
		return this.awsSnsClient;
	}

}
