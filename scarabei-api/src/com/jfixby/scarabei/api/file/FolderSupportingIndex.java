
package com.jfixby.scarabei.api.file;

import java.io.Serializable;
import java.util.HashMap;

public class FolderSupportingIndex implements Serializable {

	private static final long serialVersionUID = -5528400840395172278L;

	public static final String FILE_NAME = "index.fsi";

	public HashMap<String, FolderSupportingIndexEntry> entries = new HashMap<String, FolderSupportingIndexEntry>();
	public HashMap<String, FolderSupportingIndex> children = new HashMap<String, FolderSupportingIndex>();

}
