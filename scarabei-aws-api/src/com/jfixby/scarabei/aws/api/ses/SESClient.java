
package com.jfixby.scarabei.aws.api.ses;

public interface SESClient {

	SendEmailResult send (AmazonSimpleEmail email);

}
