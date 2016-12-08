
package com.jfixby.amazon.aws;

import com.jfixby.amazon.aws.s3.RedS3;
import com.jfixby.cmns.aws.api.AWSComponent;
import com.jfixby.cmns.aws.api.S3;

public class RedAWS implements AWSComponent {

	public RedAWS () {
		super();
	}

	final RedS3 S3 = new RedS3();

	@Override
	public S3 getS3 () {
		return this.S3;
	}

}
