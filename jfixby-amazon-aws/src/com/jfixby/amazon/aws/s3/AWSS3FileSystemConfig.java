
package com.jfixby.amazon.aws.s3;

class AWSS3FileSystemConfig {

	private String bucketName;
	private String accessKeyID;
	private String secretKeyID;

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

}
