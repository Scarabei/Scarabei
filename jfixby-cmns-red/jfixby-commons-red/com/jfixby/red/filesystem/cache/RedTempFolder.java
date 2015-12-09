package com.jfixby.red.filesystem.cache;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.file.cache.TempFolder;
import com.jfixby.cmns.api.path.AbsolutePath;

public class RedTempFolder implements TempFolder {

	private File root_folder;

	public RedTempFolder(File cache_folder, String tmp) {
		AbsolutePath<FileSystem> path = cache_folder.child(tmp)
				.getAbsoluteFilePath();
		root_folder = path.getMountPoint().newFile(path);
		root_folder.makeFolder();
	}

	@Override
	public File getRoot() {
		return root_folder;
	}

	@Override
	public void delete() {
		root_folder.delete();
	}

}
