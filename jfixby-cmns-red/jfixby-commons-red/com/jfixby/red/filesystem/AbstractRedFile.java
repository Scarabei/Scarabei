package com.jfixby.red.filesystem;

import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;

public abstract class AbstractRedFile implements File {
	@Override
	public void checkIsFolder() {
		checkExists();
		if (!this.isFolder()) {
			throw new Error("" + this + " is not a folder");
		}
	}

	@Override
	public void checkExists() {
		if (!this.exists()) {
			throw new Error(this + " does not exist.");
		}
	}

	@Override
	public void checkIsFile() {
		checkExists();
		if (!this.isFile()) {
			throw new Error(this + " does not exist.");
		}
	}

	@Override
	public File proceed(RelativePath relativePath) {
		AbsolutePath<FileSystem> file_path = this.getAbsoluteFilePath().proceed(relativePath);
		return this.getFileSystem().newFile(file_path);
	}

	@Override
	public boolean extensionIs(final String postfix) {
		return this.getName().toLowerCase().endsWith(postfix.toLowerCase());
	}

	@Override
	public ChildrenList listSubFolders() {
		FilesList listFiles = new FilesList();
		ChildrenList children = this.listChildren();
		for (File file : children) {
			if (file.isFolder()) {
				listFiles.add(file);
			}
		}

		return listFiles;
	}
}
