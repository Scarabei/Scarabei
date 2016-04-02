package com.jfixby.red.filesystem.archived;

import com.jfixby.cmns.api.file.packing.FileData;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;

public class ContentLeaf implements FileData {

    private ByteArray data = JUtils.newByteArray(0);
    private FilePointer pointer;

    public ContentLeaf(ByteArray bytes, FilePointer pointer) {
	this.data = bytes;
	this.pointer = pointer;
    }

    public long lastModified() {
	return pointer.lastModified;
    }

    @Override
    public long getSize() {
	return data.size();
    }

    @Override
    public ByteArray getBytes() {
	return data;
    }

}
