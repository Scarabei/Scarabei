
package com.jfixby.scarabei.red.filesystem.sandbox;

import java.io.IOException;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.FileSystemSandBoxComponent;

public class RedFileSystemSandBox implements FileSystemSandBoxComponent {

	@Override
	public FileSystem wrap (final String sandbox_name, final File root_folder) throws IOException {
		if (root_folder.isFile()) {
			Err.reportError("Bad " + root_folder);
		}
		if (!root_folder.exists()) {
			root_folder.makeFolder();
		}
		return new RedSandboxFileSystem(sandbox_name, root_folder);
	}

}
