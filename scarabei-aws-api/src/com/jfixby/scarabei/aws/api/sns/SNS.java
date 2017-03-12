
package com.jfixby.scarabei.aws.api.sns;

public interface SNS {

	SNSClientSpecs newSunscribeSpecs ();

	SNSClient newClient (SNSClientSpecs params);

}
