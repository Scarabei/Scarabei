
package com.jfixby.scarabei.amazon.aws.sns;

import com.jfixby.scarabei.aws.api.sns.SNSClient;
import com.jfixby.scarabei.aws.api.sns.SNSClientSpecs;
import com.jfixby.scarabei.aws.api.sns.SNSComponent;

public class RedSNS implements SNSComponent {

	@Override
	public SNSClientSpecs newClientSpecs () {
		return new RedSNSClientSpecs();
	}

	@Override
	public SNSClient newClient (final SNSClientSpecs params) {
		return new RedSNSClient(params);
	}

}
