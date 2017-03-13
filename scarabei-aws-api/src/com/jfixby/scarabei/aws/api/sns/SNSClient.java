
package com.jfixby.scarabei.aws.api.sns;

public interface SNSClient {

	SNSTopicSunscribeRequestParams newSubscribeParams ();

	SNSTopicSunscribeRequest subscribe (SNSTopicSunscribeRequestParams params);

}
