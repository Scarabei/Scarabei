
package com.jfixby.cmns.api.net.http;

import com.jfixby.cmns.api.collections.Mapping;

public interface HttpCall {

	void addRequestHeader (String parameter_name, String value);

	HttpURL getUrl ();

	boolean getUseSSL ();

	boolean getUseAgent ();

	void addRequestHeaders (Mapping<String, String> headers);

	Mapping<String, String> listRequestHeaders ();

}
