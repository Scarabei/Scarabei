
package com.jfixby.red.desktop.filesystem.win;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.file.LocalFileSystemComponent;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.md5.MD5;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractFileSystem;
import com.jfixby.red.io.JavaFileInputStream;
import com.jfixby.red.io.JavaFileOutputStream;

public class WinFileSystem extends AbstractFileSystem implements LocalFileSystemComponent {

	String application_home_path_string = System.getProperty("user.dir");

	public WinFileSystem () {

	}

	public static final String OS_SEPARATOR = "\\";

	@Override
	public WinFile newFile (final java.io.File file) {
		return this.newFile(this.resolve(file));
	}

	private AbsolutePath<FileSystem> resolve (java.io.File file) {
		// L.d();
		Debug.checkNull("file", file);
		file = file.getAbsoluteFile();

		final String path_string = file.getAbsolutePath();
		// L.d("path_string", path_string);
		// if (path_string.contains("@")) {
		// throw new Error();
		// }
		// if (path_string.contains("#input_sprites_tmp_folder#")) {
		// throw new Error();
		// }

		final List<String> steps = Collections.newList(path_string.split(OS_SEPARATOR + OS_SEPARATOR));
		// steps.print("steps");

		final RelativePath relative = JUtils.newRelativePath(steps);
		final AbsolutePath<FileSystem> path = JUtils.newAbsolutePath((FileSystem)this, relative);
		// L.d("path", path);
		// throw new Error();
		return path;
	}

	//
	@Override
	public WinFile newFile (final String java_file_path) {
		final java.io.File f = new java.io.File(Debug.checkNull("java_file_path", java_file_path));
		return this.newFile(f);
	}

	@Override
	public WinFile newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			throw new Error("Path does not belong to this filesystem: " + this);
		}
		return new WinFile(file_path, this);
	}

	@Override
	public FileOutputStream newFileOutputStream (final File output_file) throws IOException {
		if (output_file == null) {
			throw new Error("Output file is null.");
		}
		if (output_file.getFileSystem() != this) {
			throw new Error("Output file does not belong to this filesystem: " + output_file);
		}
// return new WinFileOutputStream((WinFile) output_file);
		return new JavaFileOutputStream(((WinFile)output_file).toJavaFile());
	}

	@Override
	public FileInputStream newFileInputStream (final File input_file) throws IOException {
		if (input_file == null) {
			throw new Error("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			throw new Error("Input file does not belong to this filesystem: " + input_file);
		}
		return new JavaFileInputStream(((WinFile)input_file).toJavaFile());
// return new WinFileInputStream((WinFile)input_file);
	}

	@Override
	public String nativeSeparator () {
		return OS_SEPARATOR;
	}

	@Override
	public String toString () {
		return "WinFileSystem";
	}

	@Override
	public String md5Hex (final File file) throws IOException {
		final FileInputStream is = this.newFileInputStream(file);
		is.open();
		final InputStream java_input_stream = is.toJavaInputStream();
		final String checksum = MD5.md5Stream(java_input_stream);
// java_input_stream.close();
		is.close();
		return checksum.toUpperCase();
	}

	@Override
	public File ApplicationHome () {
		return this.newFile(this.application_home_path_string);
	}

	@Override
	public java.io.File toJavaFile (final File file) {
		Debug.checkNull("file", file);
		final AbsolutePath<FileSystem> file_path = file.getAbsoluteFilePath();
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			throw new Error("Path does not belong to this filesystem: " + this);
		}
		final WinFile win_f = (WinFile)file;
		return win_f.getJavaFile();
	}

	@Override
	public String toAbsolutePathString (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			throw new Error("Path does not belong to this filesystem: " + this);
		}
		return new WinFile(file_path, this).getAbsoluteWindowsPathString();
	}

	@Override
	public File WorkspaceFolder () {
		final String application_home_path_string = System.getProperty("user.dir");
		final java.io.File workspace_mount_point = (new java.io.File(application_home_path_string)).getParentFile();
		return this.newFile(workspace_mount_point);
	}

}
