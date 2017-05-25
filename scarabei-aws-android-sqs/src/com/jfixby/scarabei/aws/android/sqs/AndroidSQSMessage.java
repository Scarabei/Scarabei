
package com.jfixby.scarabei.aws.android.sqs;

import java.util.Map;

import com.amazonaws.services.sqs.model.Message;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.aws.api.sqs.SQSMessage;

public class AndroidSQSMessage implements SQSMessage {

	private final Message awsMsg;
	private final String receiptHandle;

	public AndroidSQSMessage (final Message m) {
		this.awsMsg = m;
		this.receiptHandle = this.awsMsg.getReceiptHandle();
	}

	@Override
	public void print () {
		L.d("---SQSMessage[" + this.awsMsg.getMessageId() + "]----------------------------------");
		L.d(" ReceiptHandle", this.awsMsg.getReceiptHandle());
		L.d("     MD5OfBody", this.awsMsg.getMD5OfBody());
		L.d("          Body", this.awsMsg.getBody());
		L.d(" ReceiptHandle", this.receiptHandle);
		final Map<String, String> atr = this.awsMsg.getAttributes();
		if (atr.size() > 0) {
			L.d("    attributes", atr);

		}
	}

	@Override
	public String getBody () {
		return this.awsMsg.getBody();
	}

	@Override
	public String getReceiptHandle () {
		return this.receiptHandle;
	}

}
