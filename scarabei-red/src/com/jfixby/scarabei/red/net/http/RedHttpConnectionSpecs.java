
package com.jfixby.scarabei.red.net.http;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.net.http.HttpConnectionSpecs;
import com.jfixby.scarabei.api.net.http.HttpURL;
import com.jfixby.scarabei.api.net.http.METHOD;

public class RedHttpConnectionSpecs implements HttpConnectionSpecs {

	private HttpURL url = null;
	private boolean agent = false;
	final Map<String, String> map = Collections.newMap();
	private boolean setDoOutput = false;
	private boolean setDoInput = true;
	private METHOD method = METHOD.GET;
	private boolean octetStream;
	private boolean defaultUseCaches;
	private boolean useCaches;
	private long connectionTimeout = 3000;
	private long readTimeout = 3000;
	private boolean instanceFollowRedirects;

	@Override
	public void setURL (final HttpURL url) {
		this.url = url;
	}

	@Override
	public void setUseAgent (final boolean agent) {
		this.agent = agent;
	}

	@Override
	public HttpURL getURL () {
		return this.url;
	}

	@Override
	public boolean getUseAgent () {
		return this.agent;
	}

	@Override
	public Mapping<String, String> listRequestProperties () {
		return this.map;
	}

	@Override
	public void addRequesrProperties (final Mapping<String, String> listRequestHeaders) {
		this.map.putAll(listRequestHeaders);
	}

	@Override
	public void setDoOutput (final boolean b) {
		this.setDoOutput = b;
	}

	@Override
	public void setDoInput (final boolean b) {
		this.setDoInput = b;
	}

	@Override
	public boolean doInput () {
		return this.setDoInput;
	}

	@Override
	public boolean doOutput () {
		return this.setDoOutput;
	}

	@Override
	public void addRequesrProperty (final String key, final String value) {
		this.map.put(key, value);
	}

	@Override
	public void setMethod (final METHOD method) {
		this.method = method;
	}

	@Override
	public void setOctetStream (final boolean octetStream) {
		this.octetStream = octetStream;
	}

	@Override
	public void setDefaultUseCaches (final boolean defaultUseCaches) {
		this.defaultUseCaches = defaultUseCaches;
	}

	@Override
	public void setUseCaches (final boolean useCaches) {
		this.useCaches = useCaches;
	}

	@Override
	public void setConnectTimeout (final long connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	@Override
	public void setReadTimeout (final long readTimeout) {
		this.readTimeout = readTimeout;
	}

	@Override
	public METHOD getMethod () {
		return this.method;
	}

	@Override
	public boolean useCaches () {
		return this.useCaches;
	}

	@Override
	public boolean defaultUseCaches () {
		return this.defaultUseCaches;
	}

	@Override
	public boolean octetStream () {
		return this.octetStream;
	}

	@Override
	public long getConnectionTimeout () {
		return this.connectionTimeout;
	}

	@Override
	public long getReadTimeout () {
		return this.readTimeout;
	}

	@Override
	public void setInstanceFollowRedirects (final boolean instanceFollowRedirects) {
		this.instanceFollowRedirects = instanceFollowRedirects;
	}

	@Override
	public boolean getInstanceFollowRedirects () {
		return this.instanceFollowRedirects;
	}

}
