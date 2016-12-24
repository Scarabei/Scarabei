
package com.jfixby.scarabei.red.filesystem.archived;

import java.util.Date;

public class FilePointer {

	@Override
	public String toString () {
		if (isFile) {
			return "/" + path + " size=" + size + ", offset=" + offset + ", lastModified=" + new Date(lastModified);
		}
		return "/" + path;
	}

	public String path;
	public long offset;
	public long size;
	public boolean isFile = true;
	public long lastModified;

}
