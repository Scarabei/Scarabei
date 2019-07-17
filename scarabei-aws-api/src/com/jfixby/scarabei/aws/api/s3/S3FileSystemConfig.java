
package com.jfixby.scarabei.aws.api.s3;

import com.jfixby.scarabei.aws.api.AWSCredentialsProvider;

public class S3FileSystemConfig {

	public String bucketName;
	public AWSCredentialsProvider awsCredentialsProvider;

	@Override
	public String toString () {
		return "S3FileSystemConfig [bucketName=" + this.bucketName + ", awsCredentialsProvider=" + this.awsCredentialsProvider
			+ "]";
	}

}
