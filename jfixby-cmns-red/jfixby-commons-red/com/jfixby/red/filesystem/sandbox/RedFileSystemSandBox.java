
package com.jfixby.red.filesystem.sandbox;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.file.FileSystemSandBoxComponent;

public class RedFileSystemSandBox implements FileSystemSandBoxComponent {

	@Override
	public FileSystem wrap (final String sandbox_name, final File root_folder) throws IOException {
		if (root_folder.isFile()) {
			throw new Error("Bad " + root_folder);
		}
		if (!root_folder.exists()) {
			root_folder.makeFolder();
		}
		return new RedSandboxFileSystem(sandbox_name, root_folder);
	}

}
