package com.jfixby.red.filesystem.archived;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.packing.FileSystemUnpackingSpecs;

public class RedFileSystemUnpackingSpecs implements FileSystemUnpackingSpecs {

	private File archive_file;

	@Override
	public void setDataFile(File archive_file) {
		this.archive_file = archive_file;
	}

	@Override
	public File getDataFile() {
		return archive_file;
	}

}
