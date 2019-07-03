
package com.jfixby.scarabei.aws.api;

public class AWSCredentials implements AWSCredentialsProvider {

	public String accessKeyID;
	public String secretKeyID;
	public String regionName;

	@Override
	public String getAccessKeyID () {
		return this.accessKeyID;
	}

	@Override
	public String getSecretKeyID () {
		return this.secretKeyID;
	}

	@Override
	public String getRegionName () {
		return this.regionName;
	}

	@Override
	public String toString () {
		return "AWSCredentials [accessKeyID=" + this.accessKeyID + ", secretKeyID=" + this.secretKeyID + ", regionName="
			+ this.regionName + "]";
	}

}
