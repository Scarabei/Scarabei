
package com.jfixby.scarabei.amazon.aws.ses;

import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.ses.SESClientSpecs;

public class RedSESClientSpecs implements SESClientSpecs {

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
