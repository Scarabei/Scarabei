
package com.jfixby.scarabei.aws.api;

import com.jfixby.scarabei.aws.api.s3.S3;
import com.jfixby.scarabei.aws.api.ses.SES;
import com.jfixby.scarabei.aws.api.sns.SNS;
import com.jfixby.scarabei.aws.api.sqs.SQS;

public interface AWSComponent {

	S3 getS3 ();

	SNS getSNS ();

	SQS getSQS ();

	SES getSES ();

}
