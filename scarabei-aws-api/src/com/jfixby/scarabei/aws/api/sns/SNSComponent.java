
package com.jfixby.scarabei.aws.api.sns;

public interface SNSComponent {

	SNSClientSpecs newClientSpecs ();

	SNSClient newClient (SNSClientSpecs params);

	SNSPublishRequestSpecs newPublishRequestSpecs ();

	SNSPublishRequest newPublishRequest (SNSPublishRequestSpecs pspec);

}
