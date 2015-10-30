package com.jfixby.red.filesystem.sandbox;

import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.filesystem.FileSystem;
import com.jfixby.cmns.api.filesystem.FileSystemSandBoxComponent;

public class RedFileSystemSandBox implements FileSystemSandBoxComponent {

	@Override
	public FileSystem wrap(String sandbox_name, File root_folder) {
		if (root_folder.isFile()) {
			throw new Error("Bad " + root_folder);
		}
		if (!root_folder.exists()) {
			root_folder.makeFolder();
		}
		return new RedSandboxFileSystem(sandbox_name, root_folder);
	}

}
