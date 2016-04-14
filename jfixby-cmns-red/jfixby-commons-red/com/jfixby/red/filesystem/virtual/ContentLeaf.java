
package com.jfixby.red.filesystem.virtual;

import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;

public class ContentLeaf {

	private ByteArray data = JUtils.newByteArray(0);
	private long last_edit = System.currentTimeMillis();

	public synchronized ByteArray getData () {
		return data;
	}

	public synchronized void setData (ByteArray data) {
		this.data = data;
		last_edit = System.currentTimeMillis();
	}

	public long lastModified () {
		return last_edit;
	}

}
