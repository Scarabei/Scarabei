
package com.jfixby.scarabei.aws.android.sqs;

import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.sqs.SQSClienSpecs;

public class AndroidSQSClienSpecs implements SQSClienSpecs {

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
