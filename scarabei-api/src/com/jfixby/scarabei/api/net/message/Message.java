
package com.jfixby.scarabei.api.net.message;

import java.util.LinkedHashMap;

import com.jfixby.scarabei.api.log.L;

public final class Message implements java.io.Serializable {
	private static final long serialVersionUID = -7864576801100184653L;

	public Message (final String header) {
		this.header = header;
	}

	public Message () {

	}

	public String header;
	public LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
	public LinkedHashMap<String, java.io.Serializable> attachments = new LinkedHashMap<String, java.io.Serializable>();

	public void print () {
		L.d("---Message[" + this.header + "]------------------------");
		if (this.values != null && this.values.size() > 0) {
			L.d("     values", this.values);
		}
		if (this.attachments != null && this.attachments.size() > 0) {
			L.d("attachments", this.attachments);
		}
	}

	public Message putValue (final String key, final String value) {
		this.values.put(key, value);
		return this;
	}

}
