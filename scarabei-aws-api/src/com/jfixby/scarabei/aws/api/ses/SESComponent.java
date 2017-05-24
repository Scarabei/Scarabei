
package com.jfixby.scarabei.aws.api.ses;

public interface SESComponent {

	AmazonSimpleEmailSpecs newEmailSpecs ();

	SESClientSpecs newClientSpecs ();

	SESClient newClient (SESClientSpecs spec);

	AmazonSimpleEmail newEmail (AmazonSimpleEmailSpecs specs);

}
