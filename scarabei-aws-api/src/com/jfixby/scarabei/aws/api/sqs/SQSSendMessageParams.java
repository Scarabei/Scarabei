
package com.jfixby.scarabei.aws.api.sqs;

public interface SQSSendMessageParams {

	void setQueueURL (String queuURL);

	public String getQueueURL ();

	public String getBody ();

	void setBody (String body);

}
