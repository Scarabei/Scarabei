
package com.jfixby.scarabei.aws.desktop.sns;

import com.amazonaws.services.sns.model.PublishRequest;
import com.jfixby.scarabei.aws.api.sns.SNSPublishRequest;
import com.jfixby.scarabei.aws.api.sns.SNSPublishRequestSpecs;

public class DesktopSnSPublishRerquest implements SNSPublishRequest {

	private final String topicArn;
	private final String messageString;
	public final PublishRequest publishRequest;

	public DesktopSnSPublishRerquest (final SNSPublishRequestSpecs specs) {
		this.topicArn = specs.topicArn;
		this.messageString = specs.messageString;
		this.publishRequest = new PublishRequest(this.topicArn, this.messageString);
	}

}
