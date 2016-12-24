
package com.jfixby.scarabei.api.net.http;

import com.jfixby.scarabei.api.ComponentInstaller;

public class Http {

	static private ComponentInstaller<HttpComponent> componentInstaller = new ComponentInstaller<HttpComponent>("Http");

	public static final void installComponent (final HttpComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final HttpComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final HttpComponent component () {
		return componentInstaller.getComponent();
	}

	public static HttpURL newURL (final String url_string) {
		return invoke().newURL(url_string);
	}

	public static HttpConnection newConnection (final HttpURL url) {
		return invoke().newConnection(url);
	}

	public static HttpConnectionSpecs newConnectionSpecs () {
		return invoke().newConnectionSpecs();
	}

	public static HttpConnection newConnection (final HttpConnectionSpecs specs) {
		return invoke().newConnection(specs);
	}

	public static HttpCallParams newCallParams () {
		return invoke().newCallParams();
	}

	public static HttpCall newCall (final HttpCallParams params) {
		return invoke().newCall(params);
	}

	public static HttpCallExecutor newCallExecutor () {
		return invoke().newCallExecutor();
	}

	public static HttpFileSystemSpecs newHttpFileSystemSpecs () {
		return invoke().newHttpFileSystemSpecs();
	}

	public static HttpFileSystem newHttpFileSystem (final HttpFileSystemSpecs specs) {
		return invoke().newHttpFileSystem(specs);
	}

}
