
package com.jfixby.scarabei.red.filesystem;

import java.nio.file.Path;
import java.util.regex.Pattern;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.LocalFileSystemComponent;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;
import com.jfixby.scarabei.red.io.JavaFileInputStream;
import com.jfixby.scarabei.red.io.JavaFileOutputStream;

public class LocalFileSystem extends AbstractFileSystem implements LocalFileSystemComponent {

	@Override
	final public FileInputStream newFileInputStream (final File input_file) {
		Debug.checkNull("File", input_file);
		Debug.checkTrue("File belongs to this filesystem?", input_file.getFileSystem() == this);
		return new JavaFileInputStream(input_file.toJavaFile());
	}

	@Override
	final public FileOutputStream newFileOutputStream (final File output_file, final boolean append) {
		Debug.checkNull("File", output_file);
		Debug.checkTrue("File belongs to this filesystem?", output_file.getFileSystem() == this);
		return new JavaFileOutputStream(output_file.toJavaFile(), append);
	}

	private AbsolutePath<FileSystem> resolve (java.io.File file) {
		Debug.checkNull("file", file);
		file = file.getAbsoluteFile();

		final RelativePath relative = this.pathToRelative(file.toPath());
		final AbsolutePath<FileSystem> path = JUtils.newAbsolutePath((FileSystem)this, relative);
		return path;
	}

	private RelativePath pathToRelative (final Path path) {
		final List<String> steps = Collections.newList();
		for (final Path p : path) {
			steps.add(p.toFile().getName());
		}
		final RelativePath relative = JUtils.newRelativePath(steps);
		return relative;
	}

	@Override
	public LocalRedFile newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			Err.reportError("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", file_path.getMountPoint());
			Err.reportError("Path does not belong to this filesystem: " + this);
		}
		return new LocalRedFile(file_path, this);
	}

	@Override
	final public File newFile (final String java_file_path) {
		Debug.checkNull("java_file_path", java_file_path);

		final String splitRegex = Pattern.quote(System.getProperty("file.separator"));
		final RelativePath splittedFileName = JUtils.newRelativePath(Collections.newList(java_file_path.split(splitRegex)));
// splittedFileName.steps().print("steps");

		final File file = this.ROOT().proceed(splittedFileName);
		return file;
	}

	@Override
	final public File newFile (final java.io.File file) {
		return this.newFile(this.resolve(file));
	}

	@Override
	final public File ApplicationHome () {
		return this.newFile(this.application_home_path_string);
	}

	final String application_home_path_string = System.getProperty("user.dir");
}
