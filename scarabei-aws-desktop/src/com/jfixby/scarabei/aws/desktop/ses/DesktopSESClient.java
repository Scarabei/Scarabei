
package com.jfixby.scarabei.aws.desktop.ses;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesResult;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmail;
import com.jfixby.scarabei.aws.api.ses.SESClient;
import com.jfixby.scarabei.aws.api.ses.SESClientSpecs;
import com.jfixby.scarabei.aws.api.ses.SendEmailResult;

public class DesktopSESClient implements SESClient {

	private final AmazonSimpleEmailService aws_client;

	public DesktopSESClient (final SESClientSpecs params) {

		final AWSCredentialsProvider keys = params.getAWSCredentialsProvider();
		final String regionName = params.getSESRegionName();
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

// standardclient.set
		final String regionString = "" + com.amazonaws.regions.Region.getRegion(com.amazonaws.regions.Regions.fromName(regionName));
		standardclient.setRegion(regionString);
		this.aws_client = standardclient.build();
	}

	@Override
	public SendEmailResult send (final AmazonSimpleEmail email) {
		final DesktopAmazonSimpleEmail r_email = (DesktopAmazonSimpleEmail)email;
		verifyEmailAddress(this.aws_client, r_email.from);
		return new DesktopSendEmailResult(this.aws_client.sendEmail(r_email.aws_request));
	}

	private static void verifyEmailAddress (final AmazonSimpleEmailService ses, final String address) {
		final ListVerifiedEmailAddressesResult verifiedEmails = ses.listVerifiedEmailAddresses();
		if (verifiedEmails.getVerifiedEmailAddresses().contains(address)) {
			return;
		}

		ses.verifyEmailAddress(new VerifyEmailAddressRequest().withEmailAddress(address));
		Err.reportError("Please check the email address " + address + " to verify it");
	}

}
