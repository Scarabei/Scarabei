
package com.jfixby.scarabei.api.net.http;

import javax.net.ssl.SSLSocketFactory;

public interface HttpCallParams {

	void setURL (HttpURL http_url);

	void setUseAgent (boolean b);

	void setMethod (METHOD get);

	public void setUseSSL (boolean use_ssl);

	public boolean getUseSSL ();

	HttpURL getURL ();

	boolean getUseAgent ();

	METHOD getMethod ();

	void setSSLFactory (SSLSocketFactory sslFactory);

	SSLSocketFactory getSSLFactory ();

}
