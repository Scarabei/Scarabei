
package com.jfixby.scarabei.amazon.aws.ses;

import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmail;
import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmailSpecs;
import com.jfixby.scarabei.aws.api.ses.SES;
import com.jfixby.scarabei.aws.api.ses.SESClient;
import com.jfixby.scarabei.aws.api.ses.SESClientSpecs;

public class RedSES implements SES {

	@Override
	public AmazonSimpleEmailSpecs newEmailSpecs () {
		return new RedAmazonSimpleEmailSpecs();
	}

	@Override
	public SESClientSpecs newClientSpecs () {
		return new RedSESClientSpecs();
	}

	@Override
	public SESClient newClient (final SESClientSpecs spec) {
		return new RedSESClient(spec);
	}

	@Override
	public AmazonSimpleEmail newEmail (final AmazonSimpleEmailSpecs specs) {
		return new RedAmazonSimpleEmail(specs);
	}

}
