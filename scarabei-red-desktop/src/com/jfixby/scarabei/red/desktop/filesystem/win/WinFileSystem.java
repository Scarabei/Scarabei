
package com.jfixby.scarabei.red.desktop.filesystem.win;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.LocalFileSystemComponent;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.filesystem.AbstractLocalFileSystem;

public class WinFileSystem extends AbstractLocalFileSystem implements LocalFileSystemComponent {

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
		// Err.reportError();
		// }
		// if (path_string.contains("#input_sprites_tmp_folder#")) {
		// Err.reportError();
		// }

		final List<String> steps = Collections.newList(path_string.split(OS_SEPARATOR + OS_SEPARATOR));
		// steps.print("steps");

		final RelativePath relative = JUtils.newRelativePath(steps);
		final AbsolutePath<FileSystem> path = JUtils.newAbsolutePath((FileSystem)this, relative);
		// L.d("path", path);
		// Err.reportError();
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
			Err.reportError("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			Err.reportError("Path does not belong to this filesystem: " + this);
		}
		return new WinFile(file_path, this);
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
			Err.reportError("Path does not belong to this filesystem: " + this);
		}
		final WinFile win_f = (WinFile)file;
		return win_f.getJavaFile();
	}

	@Override
	public String toAbsolutePathString (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			Err.reportError("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			Err.reportError("Path does not belong to this filesystem: " + this);
		}
		return new WinFile(file_path, this).getAbsolutePathString();
	}

}
