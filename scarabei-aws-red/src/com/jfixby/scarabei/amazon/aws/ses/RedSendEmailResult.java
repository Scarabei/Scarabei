
package com.jfixby.scarabei.amazon.aws.ses;

import com.jfixby.scarabei.aws.api.ses.SendEmailResult;

public class RedSendEmailResult implements SendEmailResult {

	private final com.amazonaws.services.simpleemail.model.SendEmailResult awsResult;

	public RedSendEmailResult (final com.amazonaws.services.simpleemail.model.SendEmailResult sendEmail) {
		this.awsResult = sendEmail;
	}

}
