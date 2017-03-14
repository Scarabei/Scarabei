
package com.jfixby.scarabei.aws.api.sqs;

public interface SQSMessage {
	public void print ();

	public String getBody ();

	public String getReceiptHandle ();
}
