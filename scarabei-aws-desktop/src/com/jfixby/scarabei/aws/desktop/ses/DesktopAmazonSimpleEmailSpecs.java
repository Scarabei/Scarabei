
package com.jfixby.scarabei.aws.desktop.ses;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.aws.api.ses.AmazonSimpleEmailSpecs;

public class DesktopAmazonSimpleEmailSpecs implements AmazonSimpleEmailSpecs {
	final Set<String> to = Collections.newSet();
	final Set<String> bcc = Collections.newSet();
	private String subject;
	private String from;
	private String body;

	@Override
	public void setSubject (final String subject) {
		this.subject = subject;
	}

	@Override
	public void setFrom (final String from) {
		this.from = from;
	}

	@Override
	public void addTo (final String to) {
		Debug.checkNull("to", to);
		this.to.add(to);
	}

	@Override
	public void addBcc (final String bcc) {
		Debug.checkNull("bcc", bcc);
		this.bcc.add(bcc);
	}

	@Override
	public void setBody (final String string) {
		this.body = string;
	}

	@Override
	public String getSubject () {
		return this.subject;
	}

	@Override
	public String getFrom () {
		return this.from;
	}

	@Override
	public Collection<String> listTo () {
		return this.to;
	}

	@Override
	public Collection<String> listBcc () {
		return this.bcc;
	}

	@Override
	public String getBody () {
		return this.body;
	}

	@Override
	public void addTo (final Collection<String> to) {
		this.to.addAll(to);
	}

	@Override
	public void addBcc (final Collection<String> bcc) {
		this.bcc.addAll(bcc);
	}

}
