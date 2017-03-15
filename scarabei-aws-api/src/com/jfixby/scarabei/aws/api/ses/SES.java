
package com.jfixby.scarabei.aws.api.ses;

public interface SES {

	AmazonSimpleEmailSpecs newEmailSpecs ();

	SESClientSpecs newClientSpecs ();

	SESClient newClient (SESClientSpecs spec);

	AmazonSimpleEmail newEmail (AmazonSimpleEmailSpecs specs);

}
