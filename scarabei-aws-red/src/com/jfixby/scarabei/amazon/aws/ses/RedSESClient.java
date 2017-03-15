
package com.jfixby.scarabei.amazon.aws.ses;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmail;
import com.jfixby.scarabei.aws.api.ses.SESClient;
import com.jfixby.scarabei.aws.api.ses.SESClientSpecs;
import com.jfixby.scarabei.aws.api.ses.SendEmailResult;

public class RedSESClient implements SESClient {

	private final AmazonSimpleEmailService aws_client;

	public RedSESClient (final SESClientSpecs params) {

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

		final AmazonSimpleEmailServiceClientBuilder standardclient = AmazonSimpleEmailServiceClientBuilder.standard();
		standardclient.setCredentials(awsKeys);
		standardclient.setRegion("" + com.amazonaws.regions.Region.getRegion(com.amazonaws.regions.Regions.fromName(regionName)));
		this.aws_client = standardclient.build();
	}

	@Override
	public SendEmailResult send (final AmazonSimpleEmail email) {
		final RedAmazonSimpleEmail r_email = (RedAmazonSimpleEmail)email;
		return new RedSendEmailResult(this.aws_client.sendEmail(r_email.aws_request));
	}

}
