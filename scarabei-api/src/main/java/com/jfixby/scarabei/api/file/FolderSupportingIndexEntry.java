
package com.jfixby.scarabei.api.file;

import java.io.Serializable;

public class FolderSupportingIndexEntry implements Serializable {

	private static final long serialVersionUID = 715322909275904588L;

	public FolderSupportingIndexEntry () {
	}

	public static final String NAME = "FolderSupportingIndexEntry.NAME";
	public static final String IS_FILE = "FolderSupportingIndexEntry.IS_FILE";
	public static final String IS_FOLDER = "FolderSupportingIndexEntry.IS_FOLDER";
	public static final String SIZE = "FolderSupportingIndexEntry.SIZE";
	public static final String LAST_MODIFIED = "FolderSupportingIndexEntry.LAST_MODIFIED";
	public static final String HASH = "FolderSupportingIndexEntry.HASH";

	public String name;
	public boolean is_file;
	public boolean is_folder;
	public long size;
	public long lastModified;
	public String hash = "";

// public static FolderSupportingIndexEntry readEntry (final Map<String, Object> values) {
// final FolderSupportingIndexEntry entry = new FolderSupportingIndexEntry();
//
// final String name = (String)values.get(NAME);
// final String is_file = (String)values.get(IS_FILE);
// final String is_folder = (String)values.get(IS_FOLDER);
// final String size = (String)values.get(SIZE);
// final String lastModified = (String)values.get(LAST_MODIFIED);
// final String hash = (String)values.get(HASH);
//
// entry.name = name;
// entry.is_file = Boolean.parseBoolean(is_file);
// entry.is_folder = Boolean.parseBoolean(is_folder);
// entry.size = Long.parseLong(size);
// entry.lastModified = Long.parseLong(lastModified);
// entry.hash = hash;
//
// return entry;
//
// }

	@Override
	public String toString () {
		return "FileEntry[name=" + this.name + ", is_file=" + this.is_file + ", is_folder=" + this.is_folder + ", size=" + this.size
			+ ", lastModified=" + this.lastModified + ", hash=" + this.hash + "]";
	}

}
