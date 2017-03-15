
package com.jfixby.scarabei.aws.api.ses;

import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;

public interface SESClientSpecs {

	void setAWSCredentialsProvider (AWSCredentialsProvider awsKeys);

	AWSCredentialsProvider getAWSCredentialsProvider ();

	String getSESRegionName ();

	public void setSESRegionName (String regionName);

}
