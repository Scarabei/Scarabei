
package com.jfixby.scarabei.red.filesystem.virtual;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.red.filesystem.AbstractFileSystem;

public class InMemoryFileSystem extends AbstractFileSystem implements FileSystem {

	public static final String OS_SEPARATOR = "/";

	final private InMemoryFileSystemContent content = new InMemoryFileSystemContent();

	@Override
	public InMemoryFile newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			Err.reportError("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			Err.reportError("Path does not belong to this filesystem: " + file_path);
		}
		return new InMemoryFile(this, file_path);
	}

	@Override
	public FileOutputStream newFileOutputStream (final File output_file, final boolean append) {
		if (output_file == null) {
			Err.reportError("Output file is null.");
		}
		if (output_file.getFileSystem() != this) {
			Err.reportError("Output file does not belong to this filesystem: " + output_file);
		}
		return new InMemoryFileOutputStream((InMemoryFile)output_file, append);
	}

	@Override
	public FileInputStream newFileInputStream (final File input_file) {
		if (input_file == null) {
			Err.reportError("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			Err.reportError("Input file does not belong to this filesystem: " + input_file);
		}

		return new InMemoryFileInputStream((InMemoryFile)input_file);
	}

	final private String name = "InMemoryFileSystem";

	public static String toNativePathString (final String string) {
		return string;
	}

	@Override
	public String toString () {
		return this.name;
	}

	public InMemoryFileSystemContent getContent () {
		return this.content;
	}

	public String getName () {
		return this.name;
	}

}
