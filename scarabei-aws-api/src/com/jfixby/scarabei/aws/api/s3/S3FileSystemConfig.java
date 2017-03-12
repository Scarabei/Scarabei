
package com.jfixby.scarabei.aws.api.s3;

public interface S3FileSystemConfig {

	void setBucketName (String string);

	String getBucketName ();

	String getAccessKeyID ();

	String getSecretKeyID ();

	void setAccessKeyID (String accessKeyID);

	void setSecretKeyID (String secretKeyID);

	String getRegionName ();

	void setRegionName (String regionName);

}
