package com.jfixby.red.desktop.filesystem.win;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.filesystem.FileInputStream;
import com.jfixby.cmns.api.filesystem.FileOutputStream;
import com.jfixby.cmns.api.filesystem.FileSystem;
import com.jfixby.cmns.api.filesystem.LocalFileSystemComponent;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.md5.MD5;
import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.cmns.api.path.RelativePath;
import com.jfixby.red.filesystem.AbstractFileSystem;

public class WinFileSystem extends AbstractFileSystem implements
		LocalFileSystemComponent {

	String application_home_path_string = System.getProperty("user.dir");

	public WinFileSystem() {

	}

	public static final String OS_SEPARATOR = "\\";

	@Override
	public WinFile newFile(java.io.File file) {
		return newFile(resolve(file));
	}

	private AbsolutePath<FileSystem> resolve(java.io.File file) {
		// L.d();
		JUtils.checkNull("file", file);
		file = file.getAbsoluteFile();

		String path_string = file.getAbsolutePath();
		// L.d("path_string", path_string);
		// if (path_string.contains("@")) {
		// throw new Error();
		// }
		// if (path_string.contains("#input_sprites_tmp_folder#")) {
		// throw new Error();
		// }

		List<String> steps = JUtils.newList(path_string.split(OS_SEPARATOR
				+ OS_SEPARATOR));
		// steps.print("steps");

		RelativePath relative = JUtils.newRelativePath(steps);
		AbsolutePath<FileSystem> path = JUtils.newAbsolutePath((FileSystem)this, relative);
		// L.d("path", path);
		// throw new Error();
		return path;
	}

	//
	@Override
	public WinFile newFile(String java_file_path) {
		java.io.File f = new java.io.File(JUtils.checkNull("java_file_path",
				java_file_path));
		return newFile(f);
	}

	@Override
	public WinFile newFile(AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			throw new Error("Path does not belong to this filesystem: " + this);
		}
		return new WinFile(file_path, (WinFileSystem) this);
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
		return new WinFileOutputStream((WinFile) output_file);
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

		return new WinFileInputStream((WinFile) input_file);
	}

	@Override
	public String nativeSeparator() {
		return OS_SEPARATOR;
	}

	@Override
	public String toString() {
		return "WinFileSystem";
	}

	@Override
	public String md5Hex(File file) throws IOException {

		InputStream java_input_stream = this.newFileInputStream(file)
				.toJavaInputStream();
		String checksum = MD5.md5Stream(java_input_stream);
		java_input_stream.close();
		return checksum.toUpperCase();
	}

	@Override
	public File ApplicationHome() {
		return this.newFile(application_home_path_string);
	}

	@Override
	public java.io.File toJavaFile(File file) {
		JUtils.checkNull("file", file);
		AbsolutePath<FileSystem> file_path = file.getAbsoluteFilePath();
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			throw new Error("Path does not belong to this filesystem: " + this);
		}
		WinFile win_f = (WinFile) file;
		return win_f.getJavaFile();
	}

	@Override
	public String toAbsolutePathString(AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			throw new Error("Path does not belong to this filesystem: " + this);
		}
		return new WinFile(file_path, (WinFileSystem) this)
				.getAbsoluteWindowsPathString();
	}

	@Override
	public File WorkspaceFolder() {
		String application_home_path_string = System.getProperty("user.dir");
		java.io.File workspace_mount_point = (new java.io.File(
				application_home_path_string)).getParentFile();
		return this.newFile(workspace_mount_point);
	}

}
