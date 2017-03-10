
package com.jfixby.scarabei.api.net.http;

import com.jfixby.scarabei.api.collections.Mapping;

public interface HttpConnectionSpecs {

	void setURL (HttpURL url);

	void setUseAgent (boolean b);

	HttpURL getURL ();

	boolean getUseAgent ();

	Mapping<String, String> listRequestProperties ();

	void addRequesrProperties (Mapping<String, String> listRequestHeaders);

	void setDoOutput (boolean b);

	void setDoInput (boolean b);

	boolean doInput ();

	boolean doOutput ();

	void addRequesrProperty (String key, String value);

	void setMethod (METHOD post);

	void setOctetStream (boolean b);

	void setDefaultUseCaches (boolean b);

	void setUseCaches (boolean b);

	void setConnectTimeout (long serverTimeout);

	void setReadTimeout (long serverTimeout);

	METHOD getMethod ();

	boolean useCaches ();

	boolean defaultUseCaches ();

	boolean octetStream ();

	long getConnectionTimeout ();

	long getReadTimeout ();

	void setInstanceFollowRedirects (boolean instanceFollowRedirects);

	boolean getInstanceFollowRedirects ();

}
