
package com.jfixby.scarabei.aws.android.sns;

import com.jfixby.scarabei.aws.api.sns.SNSComponent;
import com.jfixby.scarabei.aws.api.sns.SNSClient;
import com.jfixby.scarabei.aws.api.sns.SNSClientSpecs;

public class AndroidSNS implements SNSComponent {

	@Override
	public SNSClientSpecs newClientSpecs () {
		return new AndroidSNSClientSpecs();
	}

	@Override
	public SNSClient newClient (final SNSClientSpecs params) {
		return new AndroidSNSClient(params);
	}

}
