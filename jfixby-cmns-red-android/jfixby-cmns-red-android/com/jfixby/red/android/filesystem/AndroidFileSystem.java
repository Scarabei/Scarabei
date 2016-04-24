
package com.jfixby.red.android.filesystem;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.android.api.Android;
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

public class AndroidFileSystem extends AbstractFileSystem implements LocalFileSystemComponent {

	String application_home_path_string = System.getProperty("user.dir");

	public AndroidFileSystem () {
		L.d("System.getProperty(user.dir)", System.getProperty("user.dir"));
		application_home_path_string = Android.getApplicationPrivateDir();
	}

	public static final String OS_SEPARATOR = "/";

	@Override
	public AndroidFile newFile (java.io.File file) {
		return newFile(resolve(file));
	}

	private AbsolutePath<FileSystem> resolve (java.io.File file) {
		Debug.checkNull("file", file);
		file = file.getAbsoluteFile();
		String path_string = file.getAbsolutePath();
		List<String> steps = Collections.newList(path_string.split(OS_SEPARATOR));
		RelativePath relative = JUtils.newRelativePath(steps);
		AbsolutePath<FileSystem> path = JUtils.newAbsolutePath((FileSystem)this, relative);
		return path;
	}

	//
	@Override
	public AndroidFile newFile (String java_file_path) {
		java.io.File f = new java.io.File(Debug.checkNull("java_file_path", java_file_path));
		return newFile(f);
	}

	@Override
	public AndroidFile newFile (AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			throw new Error("Path does not belong to this filesystem: " + this);
		}
		return new AndroidFile(file_path, (AndroidFileSystem)this);
	}

	@Override
	public FileOutputStream newFileOutputStream (File output_file) throws IOException {
		if (output_file == null) {
			throw new Error("Output file is null.");
		}
		if (output_file.getFileSystem() != this) {
			throw new Error("Output file does not belong to this filesystem: " + output_file);
		}
		return new AndroidFileOutputStream((AndroidFile)output_file);
	}

	@Override
	public FileInputStream newFileInputStream (File input_file) throws IOException {
		if (input_file == null) {
			throw new Error("Input file is null.");
		}
		if (input_file.getFileSystem() != this) {
			throw new Error("Input file does not belong to this filesystem: " + input_file);
		}

		return new AndroidFileInputStream((AndroidFile)input_file);
	}

	@Override
	public String nativeSeparator () {
		return OS_SEPARATOR;
	}

	@Override
	public String toString () {
		return "AndroidFileSystem";
	}

	@Override
	public String md5Hex (File file) throws IOException {

		InputStream java_input_stream = this.newFileInputStream(file).toJavaInputStream();
		String checksum = MD5.md5Stream(java_input_stream);
		java_input_stream.close();
		return checksum.toUpperCase();
	}

	@Override
	public File ApplicationHome () {
		return this.newFile(application_home_path_string);
	}

	@Override
	public java.io.File toJavaFile (File file) {
		Debug.checkNull("file", file);
		AbsolutePath<FileSystem> file_path = file.getAbsoluteFilePath();
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			throw new Error("Path does not belong to this filesystem: " + this);
		}
		AndroidFile win_f = (AndroidFile)file;
		return win_f.getJavaFile();
	}

	@Override
	public String toAbsolutePathString (AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			throw new Error("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			throw new Error("Path does not belong to this filesystem: " + this);
		}
		return new AndroidFile(file_path, (AndroidFileSystem)this).getAbsoluteWindowsPathString();
	}

	@Override
	public File WorkspaceFolder () {
		String application_home_path_string = System.getProperty("user.dir");
		java.io.File workspace_mount_point = (new java.io.File(application_home_path_string)).getParentFile();
		return this.newFile(workspace_mount_point);
	}

}
