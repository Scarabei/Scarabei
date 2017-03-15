
package com.jfixby.scarabei.amazon.aws;

import com.jfixby.scarabei.amazon.aws.s3.RedS3;
import com.jfixby.scarabei.amazon.aws.ses.RedSES;
import com.jfixby.scarabei.amazon.aws.sns.RedSNS;
import com.jfixby.scarabei.amazon.aws.sqs.RedSQS;
import com.jfixby.scarabei.aws.api.AWSComponent;
import com.jfixby.scarabei.aws.api.s3.S3;
import com.jfixby.scarabei.aws.api.ses.SES;
import com.jfixby.scarabei.aws.api.sns.SNS;
import com.jfixby.scarabei.aws.api.sqs.SQS;

public class RedAWS implements AWSComponent {

	public RedAWS () {
		super();
	}

	final RedS3 S3 = new RedS3();
	final RedSNS SNS = new RedSNS();
	final RedSQS SQS = new RedSQS();
	final RedSES SES = new RedSES();

	@Override
	public S3 getS3 () {
		return this.S3;
	}

	@Override
	public SNS getSNS () {
		return this.SNS;
	}

	@Override
	public SQS getSQS () {
		return this.SQS;
	}

	@Override
	public SES getSES () {
		return this.SES;
	}

}
