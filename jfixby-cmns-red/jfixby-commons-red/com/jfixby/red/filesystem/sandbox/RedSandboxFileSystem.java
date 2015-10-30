package com.jfixby.red.filesystem.sandbox;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.filesystem.FileInputStream;
import com.jfixby.cmns.api.filesystem.FileOutputStream;
import com.jfixby.cmns.api.filesystem.FileSystem;
import com.jfixby.cmns.api.md5.MD5;
import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.red.filesystem.AbstractFileSystem;

public class RedSandboxFileSystem extends AbstractFileSystem implements
		FileSystem {

	private File rootFolder;

	public File getRootFolder() {
		return rootFolder;
	}

	public void setRootFolder(File rootFolder) {
		this.rootFolder = rootFolder;
	}

	public RedSandboxFileSystem(String sandbox_name, File root_folder) {
		this.rootFolder = root_folder;
		this.name = sandbox_name;

	}

	public static final String OS_SEPARATOR = "/";

	@Override
	public File newFile(AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			throw new Error("Path does not belong to this filesystem: "
					+ file_path);
		}
		return new SandboxFile(this, file_path);
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
		return output_file.newOutputStream();
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
		return input_file.newInputStream();
	}

	@Override
	public String nativeSeparator() {
		return OS_SEPARATOR;
	}

	private String name = "";

	public static String toNativePathString(String string) {
		return string;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public String getName() {
		return "SandboxFileSystem <" + name + ">";
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
