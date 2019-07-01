
package com.jfixby.scarabei.aws.android.sns;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.aws.api.sns.SNSClient;
import com.jfixby.scarabei.aws.api.sns.SNSClientSpecs;
import com.jfixby.scarabei.aws.api.sns.SNSComponent;
import com.jfixby.scarabei.aws.api.sns.SNSPublishRequest;
import com.jfixby.scarabei.aws.api.sns.SNSPublishRequestSpecs;

public class AndroidSNS implements SNSComponent {

	@Override
	public SNSClientSpecs newClientSpecs () {
		return new AndroidSNSClientSpecs();
	}

	@Override
	public SNSClient newClient (final SNSClientSpecs params) {
		return new AndroidSNSClient(params);
	}

	@Override
	public SNSPublishRequestSpecs newPublishRequestSpecs () {
		Err.throwNotImplementedYet();
		return null;
	}

	@Override
	public SNSPublishRequest newPublishRequest (final SNSPublishRequestSpecs pspec) {
		Err.throwNotImplementedYet();
		return null;
	}

}
