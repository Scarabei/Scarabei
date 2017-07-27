
package com.jfixby.scarabei.red.filesystem.archived;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.packing.FileSystemUnpackingSpecs;

public class RedFileSystemUnpackingSpecs implements FileSystemUnpackingSpecs {

	private File archive_file;

	@Override
	public void setDataFile (File archive_file) {
		this.archive_file = archive_file;
	}

	@Override
	public File getDataFile () {
		return archive_file;
	}

}
