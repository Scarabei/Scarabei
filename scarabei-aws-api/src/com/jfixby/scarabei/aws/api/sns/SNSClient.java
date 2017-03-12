
package com.jfixby.scarabei.aws.api.sns;

public interface SNSClient {

	SNSTopicSunscribeRequestParams newSunscribeParams ();

	SNSTopicSunscribeRequest sunscribe (SNSTopicSunscribeRequestParams params);

}
