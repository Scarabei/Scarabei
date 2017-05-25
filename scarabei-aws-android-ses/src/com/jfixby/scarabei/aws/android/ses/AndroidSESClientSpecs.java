
package com.jfixby.scarabei.aws.android.ses;

import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;
import com.jfixby.scarabei.aws.api.ses.SESClientSpecs;

public class AndroidSESClientSpecs implements SESClientSpecs {

	private AWSCredentialsProvider awsKeys;
	private String regionName;

	@Override
	public void setAWSCredentialsProvider (final AWSCredentialsProvider awsKeys) {
		this.awsKeys = awsKeys;
	}

	@Override
	public AWSCredentialsProvider getAWSCredentialsProvider () {
		return this.awsKeys;
	}

	@Override
	public String getSESRegionName () {
		return this.regionName;
	}

	@Override
	public void setSESRegionName (final String regionName) {
		this.regionName = regionName;
	}

}
