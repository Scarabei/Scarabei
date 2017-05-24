
package com.jfixby.scarabei.aws.desktop.ses;

import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmail;
import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmailSpecs;
import com.jfixby.scarabei.aws.api.ses.SESComponent;
import com.jfixby.scarabei.aws.api.ses.SESClient;
import com.jfixby.scarabei.aws.api.ses.SESClientSpecs;

public class DesktopSES implements SESComponent {

	@Override
	public AmazonSimpleEmailSpecs newEmailSpecs () {
		return new DesktopAmazonSimpleEmailSpecs();
	}

	@Override
	public SESClientSpecs newClientSpecs () {
		return new DesktopSESClientSpecs();
	}

	@Override
	public SESClient newClient (final SESClientSpecs spec) {
		return new DesktopSESClient(spec);
	}

	@Override
	public AmazonSimpleEmail newEmail (final AmazonSimpleEmailSpecs specs) {
		return new DesktopAmazonSimpleEmail(specs);
	}

}
