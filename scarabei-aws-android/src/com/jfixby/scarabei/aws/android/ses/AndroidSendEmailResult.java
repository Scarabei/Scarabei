
package com.jfixby.scarabei.aws.android.ses;

import com.jfixby.scarabei.aws.api.ses.SendEmailResult;

public class AndroidSendEmailResult implements SendEmailResult {

	private final com.amazonaws.services.simpleemail.model.SendEmailResult awsResult;

	public AndroidSendEmailResult (final com.amazonaws.services.simpleemail.model.SendEmailResult sendEmail) {
		this.awsResult = sendEmail;
	}

}
