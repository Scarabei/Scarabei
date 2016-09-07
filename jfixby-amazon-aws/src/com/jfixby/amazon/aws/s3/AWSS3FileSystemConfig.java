
package com.jfixby.amazon.aws.s3;

public class AWSS3FileSystemConfig {

	private String bucketName;

	public void setBucketName (final String bucketName) {
		this.bucketName = bucketName;
	}

	public String getBucketName () {
		return this.bucketName;
	}

}
