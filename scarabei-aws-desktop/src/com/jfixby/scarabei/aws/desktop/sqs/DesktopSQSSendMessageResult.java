
package com.jfixby.scarabei.aws.desktop.sqs;

import com.amazonaws.services.sqs.model.SendMessageResult;
import com.jfixby.scarabei.aws.api.sqs.SQSSendMessageResult;

public class DesktopSQSSendMessageResult implements SQSSendMessageResult {

	private final SendMessageResult aws_result;

	public DesktopSQSSendMessageResult (final SendMessageResult result) {
		this.aws_result = result;
	}

}
