
package com.jfixby.scarabei.aws.api.sqs;

import com.jfixby.scarabei.api.collections.Collection;

public interface SQSReceiveMessageRequestResult {

	Collection<SQSMessage> listMessages ();

}
