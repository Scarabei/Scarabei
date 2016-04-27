
package com.jfixby.red.filesystem.virtual;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.red.filesystem.AbstractFileSystem;

public class VirtualFileSystem extends AbstractFileSystem implements FileSystem {

	public static final String OS_SEPARATOR = "/";

	final private VirtualFileSystemContent content = new VirtualFileSystemContent();

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
	public FileOutputStream newFileOutputStream (final File output_file) throws IOException {
		if (output_file == null) {
			throw new Error("Output file is null.");
		}
		if (output_file.getFileSystem() != this) {
			throw new Error("Output file does not belong to this filesystem: " + output_file);
		}
		return new VirtualFileOutputStream((VirtualFile)output_file);
	}

	@Override
	public FileInputStream newFileInputStream (final File input_file) throws IOException {
		if (input_file == null) {
			throw new Error("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			throw new Error("Input file does not belong to this filesystem: " + input_file);
		}
		final VirtualFile v_file = (VirtualFile)input_file;
		final ContentLeaf leaf = v_file.getContent();
		if (leaf == null) {
			throw new IOException("File not found: " + input_file);
		}
		return (FileInputStream)IO.newBufferInputStream(IO.newBuffer(leaf.getData()));
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

	public VirtualFileSystemContent getContent () {
		return this.content;
	}

	public String getName () {
		return this.name;
	}

}
