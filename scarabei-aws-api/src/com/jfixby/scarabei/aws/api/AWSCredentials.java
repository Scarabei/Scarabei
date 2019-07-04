
package com.jfixby.scarabei.aws.api;

import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;

public class AWSCredentials implements AWSCredentialsProvider {

	public static ID AWS_ACCESS_KEY () {
		return Names.newID("com_amazon_aws_ACCESS_KEY");
	}

	public static ID AWS_SECRET_KEY () {
		return Names.newID("com_amazon_aws_SECRET_KEY");
	}

	public static ID AWS_REGION_NAME () {
		return Names.newID("com_amazon_aws_REGION_NAME");
	}

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
