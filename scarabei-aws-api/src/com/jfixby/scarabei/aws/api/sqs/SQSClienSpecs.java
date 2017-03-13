
package com.jfixby.scarabei.aws.api.sqs;

import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;

public interface SQSClienSpecs {

	void setAWSCredentialsProvider (AWSCredentialsProvider awsKeys);

	AWSCredentialsProvider getAWSCredentialsProvider ();
}
