
package com.jfixby.scarabei.amazon.aws.sns;

import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.sns.SNSClientSpecs;

public class RedSNSClientSpecs implements SNSClientSpecs {

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
