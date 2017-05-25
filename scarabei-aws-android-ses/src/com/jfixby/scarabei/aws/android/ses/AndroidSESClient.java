
package com.jfixby.scarabei.aws.android.ses;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.ListVerifiedEmailAddressesResult;
import com.amazonaws.services.simpleemail.model.VerifyEmailAddressRequest;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmail;
import com.jfixby.scarabei.aws.api.ses.SESClient;
import com.jfixby.scarabei.aws.api.ses.SESClientSpecs;
import com.jfixby.scarabei.aws.api.ses.SendEmailResult;

public class AndroidSESClient implements SESClient {

	private final AmazonSimpleEmailService aws_client;

	public AndroidSESClient (final SESClientSpecs params) {

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

		final AmazonSimpleEmailServiceClient standardclient = new AmazonSimpleEmailServiceClient(awsKeys);
// standardclient.setCredentials(awsKeys);

// standardclient.set
		final Region region = com.amazonaws.regions.Region.getRegion(com.amazonaws.regions.Regions.fromName(regionName));
		standardclient.setRegion(region);
		this.aws_client = standardclient;
	}

	@Override
	public SendEmailResult send (final AmazonSimpleEmail email) {
		final AndroidAmazonSimpleEmail r_email = (AndroidAmazonSimpleEmail)email;
		verifyEmailAddress(this.aws_client, r_email.from);
		return new AndroidSendEmailResult(this.aws_client.sendEmail(r_email.aws_request));
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
