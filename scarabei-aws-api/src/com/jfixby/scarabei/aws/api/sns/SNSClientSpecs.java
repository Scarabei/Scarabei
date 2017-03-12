
package com.jfixby.scarabei.aws.api.sns;

import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;

public interface SNSClientSpecs {

	void setAWSCredentialsProvider (AWSCredentialsProvider awsKeys);

	AWSCredentialsProvider getAWSCredentialsProvider ();

}
