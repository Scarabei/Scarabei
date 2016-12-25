
package com.jfixby.scarabei.api.file;

import java.io.Serializable;
import java.util.HashMap;

public class FolderSupportingIndex implements Serializable {

	private static final long serialVersionUID = 1798498458960877250L;

	public FolderSupportingIndex () {
	}

	public static final String FILE_NAME = "index.fsi";

	public static final String ENTRIES = "FolderSupportingIndex.ENTRIES";
	public static final String CHILDREN = "FolderSupportingIndex.CHILDREN";

	public HashMap<String, FolderSupportingIndexEntry> entries = new HashMap<String, FolderSupportingIndexEntry>();
	public HashMap<String, FolderSupportingIndex> children = new HashMap<String, FolderSupportingIndex>();

// public static FolderSupportingIndex readIndex (final Map<String, Object> values) {
// final FolderSupportingIndex entry = new FolderSupportingIndex();
// entry.children = (HashMap<String, FolderSupportingIndex>)values.get(CHILDREN);
// entry.entries = (HashMap<String, FolderSupportingIndexEntry>)values.get(ENTRIES);
// return entry;
//
// }
}
