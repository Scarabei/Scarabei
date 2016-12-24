
package com.jfixby.scarabei.api.file;

import java.io.Serializable;

public class FolderSupportingIndexEntry implements Serializable {

	@Override
	public String toString () {
		return "FileEntry[name=" + this.name + ", is_file=" + this.is_file + ", is_folder=" + this.is_folder + ", size=" + this.size
			+ ", lastModified=" + this.lastModified + ", hash=" + this.hash + "]";
	}

	private static final long serialVersionUID = -555688080954733840L;
	public String name;
	public boolean is_file;
	public boolean is_folder;
	public long size;
	public long lastModified;
	public String hash = "";

}
