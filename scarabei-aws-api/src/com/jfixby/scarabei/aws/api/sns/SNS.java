
package com.jfixby.scarabei.aws.api.sns;

public interface SNS {

	SNSClientSpecs newClientSpecs ();

	SNSClient newClient (SNSClientSpecs params);

}
