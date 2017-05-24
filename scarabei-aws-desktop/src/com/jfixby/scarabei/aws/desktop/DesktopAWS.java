
package com.jfixby.scarabei.aws.desktop;

import com.jfixby.scarabei.aws.api.AWSComponent;
import com.jfixby.scarabei.aws.api.s3.S3;
import com.jfixby.scarabei.aws.api.ses.SES;
import com.jfixby.scarabei.aws.api.sns.SNS;
import com.jfixby.scarabei.aws.api.sqs.SQS;
import com.jfixby.scarabei.aws.desktop.s3.DesktopS3;
import com.jfixby.scarabei.aws.desktop.ses.DesktopSES;
import com.jfixby.scarabei.aws.desktop.sns.DesktopSNS;
import com.jfixby.scarabei.aws.desktop.sqs.DesktopSQS;

public class DesktopAWS implements AWSComponent {

	public DesktopAWS () {
		super();
	}

	final DesktopS3 S3 = new DesktopS3();
	final DesktopSNS SNS = new DesktopSNS();
	final DesktopSQS SQS = new DesktopSQS();
	final DesktopSES SES = new DesktopSES();

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
