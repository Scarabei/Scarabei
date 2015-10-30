package com.jfixby.red.filesystem.virtual;

public class ContentLeaf {

	private byte[] data = new byte[0];
	private long last_edit = System.currentTimeMillis();

	public synchronized byte[] getData() {
		return data;
	}

	public synchronized void setData(byte[] data) {
		this.data = data;
		last_edit = System.currentTimeMillis();
	}

	public long lastModified() {
		return last_edit;
	}

}
