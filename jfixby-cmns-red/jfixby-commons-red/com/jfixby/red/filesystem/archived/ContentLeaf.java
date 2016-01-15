package com.jfixby.red.filesystem.archived;

import com.jfixby.cmns.api.file.packing.FileData;

public class ContentLeaf implements FileData {

	private byte[] data = new byte[0];
	private FilePointer pointer;

	public ContentLeaf(byte[] bytes, FilePointer pointer) {
		this.data = bytes;
		this.pointer = pointer;
	}

	public long lastModified() {
		return pointer.lastModified;
	}

	@Override
	public long getSize() {
		return data.length;
	}

	@Override
	public byte[] getBytes() {
		return data;
	}

}
