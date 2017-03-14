
package com.jfixby.scarabei.aws.api.sqs;

public interface SQSDeleteMessageParams {

	void setQueueURL (String queueURL);

	void setMessageReceiptHandle (String messageReceiptHandle);

	public String getQueueURL ();

	public String getMessageReceiptHandle ();

}
