
package com.jfixby.scarabei.aws.api.sqs;

import com.jfixby.scarabei.api.collections.Collection;

public interface SQSReceiveMessageResult {

	Collection<SQSMessage> listMessages ();

}
