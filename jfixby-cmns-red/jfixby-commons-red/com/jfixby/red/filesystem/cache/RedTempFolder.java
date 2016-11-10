
package com.jfixby.red.filesystem.cache;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.file.cache.TempFolder;
import com.jfixby.cmns.api.util.path.AbsolutePath;

public class RedTempFolder implements TempFolder {

	private final File root_folder;

	public RedTempFolder (final File cache_folder, final String tmp) throws IOException {
		final AbsolutePath<FileSystem> path = cache_folder.child(tmp).getAbsoluteFilePath();
		this.root_folder = path.getMountPoint().newFile(path);
		this.root_folder.makeFolder();
	}

	@Override
	public File getRoot () {
		return this.root_folder;
	}

	@Override
	public void delete () throws IOException {
		this.root_folder.delete();
	}

}
