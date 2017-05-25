
package com.jfixby.scarabei.aws.android.ses;

import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmail;
import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmailSpecs;
import com.jfixby.scarabei.aws.api.ses.SESComponent;
import com.jfixby.scarabei.aws.api.ses.SESClient;
import com.jfixby.scarabei.aws.api.ses.SESClientSpecs;

public class AndroidSES implements SESComponent {

	@Override
	public AmazonSimpleEmailSpecs newEmailSpecs () {
		return new AndroidAmazonSimpleEmailSpecs();
	}

	@Override
	public SESClientSpecs newClientSpecs () {
		return new AndroidSESClientSpecs();
	}

	@Override
	public SESClient newClient (final SESClientSpecs spec) {
		return new AndroidSESClient(spec);
	}

	@Override
	public AmazonSimpleEmail newEmail (final AmazonSimpleEmailSpecs specs) {
		return new AndroidAmazonSimpleEmail(specs);
	}

}
