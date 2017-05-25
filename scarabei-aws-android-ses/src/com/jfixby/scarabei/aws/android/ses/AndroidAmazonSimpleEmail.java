
package com.jfixby.scarabei.aws.android.ses;

import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmail;
import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmailSpecs;

public class AndroidAmazonSimpleEmail implements AmazonSimpleEmail {
	private final String subject;
	final String from;
	private final Collection<String> to;
	private final Collection<String> bcc;
	private final String body;
	final Message aws_message;
	final SendEmailRequest aws_request;

	public AndroidAmazonSimpleEmail (final AmazonSimpleEmailSpecs specs) {
		this.subject = specs.getSubject();
		this.from = specs.getFrom();

		this.to = specs.listTo();
		this.bcc = specs.listBcc();

		this.body = specs.getBody();

		final Destination destination = new Destination();
		destination.withBccAddresses(this.bcc.toJavaList());
		destination.withToAddresses(this.to.toJavaList());

		// Create the subject and body of the message.
		final Content subject = new Content().withData(this.subject);
		final Content textBody = new Content().withData(this.body);
		final Body body = new Body().withText(textBody);

		// Create a message with the specified subject and body.
		this.aws_message = new Message().withSubject(subject).withBody(body);
		this.aws_request = new SendEmailRequest().withSource(this.from).withDestination(destination).withMessage(this.aws_message);
	}

}
