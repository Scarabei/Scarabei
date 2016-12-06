
package com.jfixby.red.desktop.filesystem.unix;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.file.LocalFileSystemComponent;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractLocalFileSystem;

public class UnixFileSystem extends AbstractLocalFileSystem implements LocalFileSystemComponent {

	String application_home_path_string = System.getProperty("user.dir");

	public UnixFileSystem () {

	}

	public static final String OS_SEPARATOR = "/";

	@Override
	public UnixFile newFile (final java.io.File file) {
		return this.newFile(this.resolve(file));
	}

	private AbsolutePath<FileSystem> resolve (java.io.File file) {
		Debug.checkNull("file", file);
		file = file.getAbsoluteFile();
		final String path_string = file.getAbsolutePath();
		final List<String> steps = Collections.newList(path_string.split(OS_SEPARATOR));
		final RelativePath relative = JUtils.newRelativePath(steps);
		final AbsolutePath<FileSystem> path = JUtils.newAbsolutePath((FileSystem)this, relative);
		return path;
	}

	//
	@Override
	public UnixFile newFile (final String java_file_path) {
		final java.io.File f = new java.io.File(Debug.checkNull("java_file_path", java_file_path));
		return this.newFile(f);
	}

	@Override
	public UnixFile newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			Err.reportError("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			Err.reportError("Path does not belong to this filesystem: " + this);
		}
		return new UnixFile(file_path, this);
	}

	@Override
	public String nativeSeparator () {
		return OS_SEPARATOR;
	}

	@Override
	public String toString () {
		return "UnixFileSystem";
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
		final UnixFile win_f = (UnixFile)file;
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
		return new UnixFile(file_path, this).getAbsolutePathString();
	}

}
