
package com.jfixby.scarabei.aws.desktop.sns;

import com.jfixby.scarabei.aws.api.sns.SNS;
import com.jfixby.scarabei.aws.api.sns.SNSClient;
import com.jfixby.scarabei.aws.api.sns.SNSClientSpecs;

public class DesktopSNS implements SNS {

	@Override
	public SNSClientSpecs newClientSpecs () {
		return new DesktopSNSClientSpecs();
	}

	@Override
	public SNSClient newClient (final SNSClientSpecs params) {
		return new DesktopSNSClient(params);
	}

}
