
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

// L.d("---SQSMessage[" + this.awsMsg.getMessageId() + "]----------------------------------");
// System.out.println(" ReceiptHandle: " + message.getReceiptHandle());
// System.out.println(" MD5OfBody: " + message.getMD5OfBody());
// System.out.println(" Body: " + message.getBody());
// for (Entry<String, String> entry : message.getAttributes().entrySet()) {
// System.out.println(" Attribute");
// System.out.println(" Name: " + entry.getKey());
// System.out.println(" Value: " + entry.getValue());
// }
	}

}
