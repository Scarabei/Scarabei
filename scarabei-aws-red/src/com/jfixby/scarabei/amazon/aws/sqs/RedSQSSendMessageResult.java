
package com.jfixby.scarabei.amazon.aws.sqs;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.jfixby.scarabei.aws.api.sqs.SQSSendMessageResult;

public class RedSQSSendMessageResult implements SQSSendMessageResult {

	private final SendMessageResult aws_result;

	public RedSQSSendMessageResult (final SendMessageResult result) {
		this.aws_result = result;
	}

}
