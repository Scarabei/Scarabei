
package com.jfixby.scarabei.amazon.aws.sns;

import com.jfixby.scarabei.aws.api.sns.SNS;
import com.jfixby.scarabei.aws.api.sns.SNSClient;
import com.jfixby.scarabei.aws.api.sns.SNSClientSpecs;

public class RedSNS implements SNS {

	@Override
	public SNSClientSpecs newSunscribeSpecs () {
		return new RedSNSClientSpecs();
	}

	@Override
	public SNSClient newClient (final SNSClientSpecs params) {
		return new RedSNSClient(params);
	}

}
