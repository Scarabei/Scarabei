
package com.jfixby.scarabei.aws.desktop.ses;

import com.jfixby.scarabei.aws.api.ses.SendEmailResult;

public class DesktopSendEmailResult implements SendEmailResult {

	private final com.amazonaws.services.simpleemail.model.SendEmailResult awsResult;

	public DesktopSendEmailResult (final com.amazonaws.services.simpleemail.model.SendEmailResult sendEmail) {
		this.awsResult = sendEmail;
	}

}
