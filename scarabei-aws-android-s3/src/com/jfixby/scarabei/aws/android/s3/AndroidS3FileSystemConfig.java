
package com.jfixby.scarabei.aws.android.s3;

import com.jfixby.scarabei.aws.api.s3.S3FileSystemConfig;

class AndroidS3FileSystemConfig implements S3FileSystemConfig {

	private String bucketName;
	private String accessKeyID;
	private String secretKeyID;
	private String regionName;

	@Override
	public void setBucketName (final String bucketName) {
		this.bucketName = bucketName;
	}

	@Override
	public String getBucketName () {
		return this.bucketName;
	}

	@Override
	public String getAccessKeyID () {
		return this.accessKeyID;
	}

	@Override
	public String getSecretKeyID () {
		return this.secretKeyID;
	}

	@Override
	public void setAccessKeyID (final String accessKeyID) {
		this.accessKeyID = accessKeyID;
	}

	@Override
	public void setSecretKeyID (final String secretKeyID) {
		this.secretKeyID = secretKeyID;
	}

	@Override
	public String getRegionName () {
		return this.regionName;
	}

	@Override
	public void setRegionName (final String regionName) {
		this.regionName = regionName;
	}

}
