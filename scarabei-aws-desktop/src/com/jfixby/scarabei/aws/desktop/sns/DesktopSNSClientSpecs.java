
package com.jfixby.scarabei.aws.desktop.sns;

import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.sns.SNSClientSpecs;

public class DesktopSNSClientSpecs implements SNSClientSpecs {

	private AWSCredentialsProvider awsKeys;

	@Override
	public void setAWSCredentialsProvider (final AWSCredentialsProvider awsKeys) {
		this.awsKeys = awsKeys;
	}

	@Override
	public AWSCredentialsProvider getAWSCredentialsProvider () {
		return this.awsKeys;
	}

}
