package com.jfixby.red.filesystem.archived;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.packing.FileSystemUnpackingSpecs;
import com.jfixby.cmns.api.io.InputStream;

public class RedFileSystemUnpackingSpecs implements FileSystemUnpackingSpecs {

	@Override
	public void setTargetFolder(File local_resource_folder) {
	}

	@Override
	public void setInputStream(InputStream is) {
	}

	@Override
	public File getTargetFolder() {
		return null;
	}

	@Override
	public InputStream getInputStream() {
		return null;
	}

}
