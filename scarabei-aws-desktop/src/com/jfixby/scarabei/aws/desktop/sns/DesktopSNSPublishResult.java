
package com.jfixby.scarabei.aws.desktop.sns;

import com.amazonaws.services.sns.model.PublishResult;
import com.jfixby.scarabei.aws.api.sns.SNSPublishResult;

public class DesktopSNSPublishResult implements SNSPublishResult {

	private final PublishResult awspublishResponse;

	public DesktopSNSPublishResult (final PublishResult publishResponse) {
		this.awspublishResponse = publishResponse;
	}

	@Override
	public String toString () {
		return "RedSNSPublishResult [awspublishResponse=" + this.awspublishResponse + "]";
	}

}
