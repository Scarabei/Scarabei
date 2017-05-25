
package com.jfixby.scarabei.aws.android.sqs;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.jfixby.scarabei.aws.api.sqs.SQSSendMessageResult;

public class AndroidSQSSendMessageResult implements SQSSendMessageResult {

	private final SendMessageResult aws_result;

	public AndroidSQSSendMessageResult (final SendMessageResult result) {
		this.aws_result = result;
	}

}
