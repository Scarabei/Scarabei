
package com.jfixby.red.filesystem.archived;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.util.path.RelativePath;

public class FileTag {

	public RelativePath path;
	public File file;

	@Override
	public String toString () {
		return path + "";
	}

	public FileTag (File folder, RelativePath path) {
		this.file = folder;
		this.path = path;

	}

}
