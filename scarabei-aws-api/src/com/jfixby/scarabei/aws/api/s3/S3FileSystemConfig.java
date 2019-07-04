
package com.jfixby.scarabei.aws.api.s3;

public class S3FileSystemConfig {

	public String bucketName;
	public String accessKeyID;
	public String secretKeyID;
	public String regionName;

	public void setBucketName (final String bucketName) {
		this.bucketName = bucketName;
	}

	public String getBucketName () {
		return this.bucketName;
	}

	public String getAccessKeyID () {
		return this.accessKeyID;
	}

	public String getSecretKeyID () {
		return this.secretKeyID;
	}

	public void setAccessKeyID (final String accessKeyID) {
		this.accessKeyID = accessKeyID;
	}

	public void setSecretKeyID (final String secretKeyID) {
		this.secretKeyID = secretKeyID;
	}

	public String getRegionName () {
		return this.regionName;
	}

	public void setRegionName (final String regionName) {
		this.regionName = regionName;
	}

	@Override
	public String toString () {
		return "S3FileSystemConfig [bucketName=" + this.bucketName + ", accessKeyID=" + this.accessKeyID + ", secretKeyID="
			+ this.secretKeyID + ", regionName=" + this.regionName + "]";
	}

}
