
package com.jfixby.red.filesystem.http.descript;

import java.io.Serializable;

public class HttpFileEntry implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -555688080954733840L;
	public String name;
	public boolean is_file;
	public boolean is_folder;
	public long size;
	public long lastModified;
	public String hash;

}
