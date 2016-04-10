
package com.jfixby.cmns.api.net.http;

import com.jfixby.cmns.api.ComponentInstaller;

public class Http {

	static private ComponentInstaller<HttpComponent> componentInstaller = new ComponentInstaller<HttpComponent>("Http");

	public static final void installComponent (HttpComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final HttpComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final HttpComponent component () {
		return componentInstaller.getComponent();
	}

	public static HttpURL newURL (String url_string) {
		return invoke().newURL(url_string);
	}

	public static HttpConnection newConnection (HttpURL url) {
		return invoke().newConnection(url);
	}

	public static HttpConnectionSpecs newConnectionSpecs () {
		return invoke().newConnectionSpecs();
	}

	public static HttpConnection newConnection (HttpConnectionSpecs specs) {
		return invoke().newConnection(specs);
	}

	public static HttpCallParams newCallParams () {
		return invoke().newCallParams();
	}

	public static HttpCall newCall (HttpCallParams params) {
		return invoke().newCall(params);
	}

	public static HttpCallExecutor newCallExecutor () {
		return invoke().newCallExecutor();
	}
}
