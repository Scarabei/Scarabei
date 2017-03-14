
package com.jfixby.scarabei.aws.api.sqs;

import com.jfixby.scarabei.api.collections.Collection;

public interface SQSClient {
	SQSReceiveMessageResult receive (SQSReceiveMessageRequest request);

	SQSCreateQueueResult createQueue (SQSCreateQueueParams createQueueRequestParams);

	Collection<String> listAllSQSUrls ();

	SQSSendMessageResult sendMessage (SQSSendMessageParams sendParams);

	SQSDeleteMessageResult deleteMessage (SQSDeleteMessageParams delete);
}
