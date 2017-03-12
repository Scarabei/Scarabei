
package com.jfixby.scarabei.aws.api;

public interface AWSCredentialsProvider {

	String getAccessKeyID ();

	String getSecretKeyID ();

	String getRegionName ();

}
