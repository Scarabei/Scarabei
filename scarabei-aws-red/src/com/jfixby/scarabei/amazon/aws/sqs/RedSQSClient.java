
package com.jfixby.scarabei.amazon.aws.sqs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.sqs.SQSClienSpecs;
import com.jfixby.scarabei.aws.api.sqs.SQSClient;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequestResult;

public class RedSQSClient implements SQSClient {

	private AmazonSQSClient awsSQSClient;

	public RedSQSClient (final SQSClienSpecs params) {
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
		this.awsSQSClient = new AmazonSQSClient(awsKeys);
		this.awsSQSClient.setRegion(com.amazonaws.regions.Region.getRegion(com.amazonaws.regions.Regions.fromName(regionName)));

	}

	AmazonSQSClient getSQSClient () {
		return this.awsSQSClient;
	}

	@Override
	public SQSReceiveMessageRequestResult receive (final SQSReceiveMessageRequest request) {
		return new RedSQSReceiveMessageRequestResult((RedSQSReceiveMessageRequest)request, this.awsSQSClient);
	}

}
