
package com.jfixby.scarabei.aws.desktop.sns;

import com.jfixby.scarabei.aws.api.sns.SNSClient;
import com.jfixby.scarabei.aws.api.sns.SNSClientSpecs;
import com.jfixby.scarabei.aws.api.sns.SNSComponent;

public class DesktopSNS implements SNSComponent {

	@Override
	public SNSClientSpecs newClientSpecs () {
		return new DesktopSNSClientSpecs();
	}

	@Override
	public SNSClient newClient (final SNSClientSpecs params) {
		return new DesktopSNSClient(params);
	}

}
