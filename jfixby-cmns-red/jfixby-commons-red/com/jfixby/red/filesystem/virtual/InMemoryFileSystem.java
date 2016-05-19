
package com.jfixby.red.filesystem.virtual;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.red.filesystem.AbstractFileSystem;

public class InMemoryFileSystem extends AbstractFileSystem implements FileSystem {

	public static final String OS_SEPARATOR = "/";

	final private InMemoryFileSystemContent content = new InMemoryFileSystemContent();

	@Override
	public VirtualFile newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			throw new Error("Path does not belong to this filesystem: " + file_path);
		}
		return new VirtualFile(this, file_path);
	}

	@Override
	public FileOutputStream newFileOutputStream (final File output_file) {
		if (output_file == null) {
			throw new Error("Output file is null.");
		}
		if (output_file.getFileSystem() != this) {
			throw new Error("Output file does not belong to this filesystem: " + output_file);
		}
		return new VirtualFileOutputStream((VirtualFile)output_file);
	}

	@Override
	public FileInputStream newFileInputStream (final File input_file) {
		if (input_file == null) {
			throw new Error("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			throw new Error("Input file does not belong to this filesystem: " + input_file);
		}

		return new VirtualFileInputStream((VirtualFile)input_file);
	}

	@Override
	public String nativeSeparator () {
		return OS_SEPARATOR;
	}

	final private String name = "VirtualFileSystem";

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
