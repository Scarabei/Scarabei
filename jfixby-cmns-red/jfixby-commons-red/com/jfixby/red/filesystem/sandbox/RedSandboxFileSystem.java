
package com.jfixby.red.filesystem.sandbox;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.red.filesystem.AbstractFileSystem;

public class RedSandboxFileSystem extends AbstractFileSystem implements FileSystem {

	private File rootFolder;

	public File getRootFolder () {
		return this.rootFolder;
	}

	public void setRootFolder (final File rootFolder) {
		this.rootFolder = rootFolder;
	}

	public RedSandboxFileSystem (final String sandbox_name, final File root_folder) {
		this.rootFolder = root_folder;
		this.name = sandbox_name;

	}

	public static final String OS_SEPARATOR = "/";

	@Override
	public File newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			throw new Error("Path does not belong to this filesystem: " + file_path);
		}
		return new SandboxFile(this, file_path);
	}

	@Override
	public FileOutputStream newFileOutputStream (final File output_file) {
		if (output_file == null) {
			throw new Error("Output file is null.");
		}
		if (output_file.getFileSystem() != this) {
			throw new Error("Output file does not belong to this filesystem: " + output_file);
		}
		return output_file.newOutputStream();
	}

	@Override
	public FileInputStream newFileInputStream (final File input_file) {
		if (input_file == null) {
			throw new Error("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			throw new Error("Input file does not belong to this filesystem: " + input_file);
		}
		return input_file.newInputStream();
	}

	@Override
	public String nativeSeparator () {
		return OS_SEPARATOR;
	}

	private String name = "";

	public static String toNativePathString (final String string) {
		return string;
	}

	@Override
	public String toString () {
		return this.getName();
	}

	public String getName () {
		return "SandboxFileSystem <" + this.name + ">";
	}

}
