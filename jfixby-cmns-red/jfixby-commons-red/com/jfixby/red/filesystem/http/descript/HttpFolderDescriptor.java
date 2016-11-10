
package com.jfixby.red.filesystem.http.descript;

import java.io.Serializable;
import java.util.HashMap;

public class HttpFolderDescriptor implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5528400840395172278L;

	public static final String HTTP_FOLDER_DESCRIPTOR_FILE_NAME = "http.folder-descriptor";

	public HashMap<String, HttpFileEntry> entries = new HashMap<String, HttpFileEntry>();
	public HashMap<String, HttpFolderDescriptor> children = new HashMap<String, HttpFolderDescriptor>();

}
