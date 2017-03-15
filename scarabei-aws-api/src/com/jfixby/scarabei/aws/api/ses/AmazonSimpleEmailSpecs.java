
package com.jfixby.scarabei.aws.api.ses;

import com.jfixby.scarabei.api.collections.Collection;

public interface AmazonSimpleEmailSpecs {

	void setSubject (String subject);

	void setFrom (String from);

	void addTo (String to);

	void addBcc (String bcc);

	void addTo (Collection<String> to);

	void addBcc (Collection<String> bcc);

	void setBody (String string);

	String getSubject ();

	String getFrom ();

	Collection<String> listTo ();

	Collection<String> listBcc ();

	String getBody ();
}
