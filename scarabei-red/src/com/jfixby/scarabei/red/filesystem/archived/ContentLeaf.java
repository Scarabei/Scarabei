
package com.jfixby.scarabei.red.filesystem.archived;

import com.jfixby.scarabei.api.file.packing.FileData;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.JUtils;

public class ContentLeaf implements FileData {

	private ByteArray data = JUtils.newByteArray(0);
	private FilePointer pointer;

	public ContentLeaf (ByteArray bytes, FilePointer pointer) {
		this.data = bytes;
		this.pointer = pointer;
	}

	public long lastModified () {
		return pointer.lastModified;
	}

	@Override
	public long getSize () {
		return data.size();
	}

	@Override
	public ByteArray getBytes () {
		return data;
	}

}
