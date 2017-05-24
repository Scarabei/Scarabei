
package com.jfixby.scarabei.aws.desktop.sqs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.sqs.SQSClienSpecs;
import com.jfixby.scarabei.aws.api.sqs.SQSClient;
import com.jfixby.scarabei.aws.api.sqs.SQSCreateQueueParams;
import com.jfixby.scarabei.aws.api.sqs.SQSCreateQueueResult;
import com.jfixby.scarabei.aws.api.sqs.SQSDeleteMessageParams;
import com.jfixby.scarabei.aws.api.sqs.SQSDeleteMessageResult;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageRequest;
import com.jfixby.scarabei.aws.api.sqs.SQSReceiveMessageResult;
import com.jfixby.scarabei.aws.api.sqs.SQSSendMessageParams;
import com.jfixby.scarabei.aws.api.sqs.SQSSendMessageResult;

public class DesktopSQSClient implements SQSClient {

	private AmazonSQSClient awsSQSClient;

	public DesktopSQSClient (final SQSClienSpecs params) {
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
	public SQSReceiveMessageResult receive (final SQSReceiveMessageRequest request) {
		return new DesktopSQSReceiveMessageRequestResult((DesktopSQSReceiveMessageRequest)request, this.awsSQSClient);
	}

	@Override
	public SQSCreateQueueResult createQueue (final SQSCreateQueueParams createQueueRequestParams) {
		return new DesktopSQSCreateQueueResult(createQueueRequestParams, this.awsSQSClient);
	}

	@Override
	public Collection<String> listAllSQSUrls () {
		final ListQueuesResult list = this.awsSQSClient.listQueues();
		return Collections.newList(list.getQueueUrls());
	}

	@Override
	public SQSSendMessageResult sendMessage (final SQSSendMessageParams sendParams) {
		final String queueUrl = sendParams.getQueueURL();
		final String messageBody = sendParams.getBody();
		final SendMessageResult result = this.awsSQSClient.sendMessage(queueUrl, messageBody);
		return new DesktopSQSSendMessageResult(result);
	}

	@Override
	public SQSDeleteMessageResult deleteMessage (final SQSDeleteMessageParams delete) {
		final String queueUrl = delete.getQueueURL();
		final String receiptHandle = delete.getMessageReceiptHandle();
		this.awsSQSClient.deleteMessage(queueUrl, receiptHandle);
		return new DesktopSQSDeleteMessageResult();
	}

}
