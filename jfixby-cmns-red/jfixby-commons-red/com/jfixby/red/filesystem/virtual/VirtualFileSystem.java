package com.jfixby.red.filesystem.virtual;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.filesystem.FileInputStream;
import com.jfixby.cmns.api.filesystem.FileOutputStream;
import com.jfixby.cmns.api.filesystem.FileSystem;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.md5.MD5;
import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.red.filesystem.AbstractFileSystem;

public class VirtualFileSystem extends AbstractFileSystem implements
		 FileSystem {

	public static final String OS_SEPARATOR = "/";

	final private VirtualFileSystemContent content = new VirtualFileSystemContent();

	@Override
	public VirtualFile newFile(AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			throw new Error("Path does not belong to this filesystem: "
					+ file_path);
		}
		return new VirtualFile(this, file_path);
	}

	@Override
	public FileOutputStream newFileOutputStream(File output_file)
			throws IOException {
		if (output_file == null) {
			throw new Error("Output file is null.");
		}
		if (output_file.getFileSystem() != this) {
			throw new Error("Output file does not belong to this filesystem: "
					+ output_file);
		}
		return new VirtualFileOutputStream((VirtualFile) output_file);
	}

	@Override
	public FileInputStream newFileInputStream(File input_file)
			throws IOException {
		if (input_file == null) {
			throw new Error("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			throw new Error("Input file does not belong to this filesystem: "
					+ input_file);
		}
		VirtualFile v_file = (VirtualFile) input_file;
		ContentLeaf leaf = v_file.getContent();
		if (leaf == null) {
			throw new IOException("File not found: " + input_file);
		}
		return (FileInputStream) IO.newBufferInputStream(IO.newBuffer(leaf
				.getData()));
	}

	@Override
	public String nativeSeparator() {
		return OS_SEPARATOR;
	}

	final private String name = "VirtualFileSystem";

	public static String toNativePathString(String string) {
		return string;
	}



	@Override
	public String toString() {
		return name;
	}

	public VirtualFileSystemContent getContent() {
		return this.content;
	}

	public String getName() {
		return name;
	}

	@Override
	public String md5Hex(File file) throws IOException {
		InputStream java_input_stream = this.newFileInputStream(file)
				.toJavaInputStream();
		String checksum = MD5.md5Stream(java_input_stream);
		java_input_stream.close();
		return checksum.toUpperCase();
	}


}
