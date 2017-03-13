
package com.jfixby.scarabei.amazon.aws.sqs;

import java.util.Map;

import com.amazonaws.services.sqs.model.Message;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.aws.api.sqs.SQSMessage;

public class RedSQSMessage implements SQSMessage {

	private final Message awsMsg;

	public RedSQSMessage (final Message m) {
		this.awsMsg = m;
	}

	@Override
	public void print () {
		L.d("---SQSMessage[" + this.awsMsg.getMessageId() + "]----------------------------------");
		L.d(" ReceiptHandle", this.awsMsg.getReceiptHandle());
		L.d("     MD5OfBody", this.awsMsg.getMD5OfBody());
		L.d("          Body", this.awsMsg.getBody());
		final Map<String, String> atr = this.awsMsg.getAttributes();
		if (atr.size() > 0) {
			L.d("    attributes", atr);

		}
	}

	@Override
	public String getBody () {
		return this.awsMsg.getBody();
	}

}
