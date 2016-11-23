
package com.jfixby.cmns.api.net.message;

import java.util.LinkedHashMap;

import com.jfixby.cmns.api.log.L;

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
		L.d("     values", this.values);
		L.d("attachments", this.attachments);

	}

}
