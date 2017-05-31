
package com.jfixby.r3.fokker.filesystem.assets;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.red.filesystem.AbstractFileSystem;
import com.jfixby.scarabei.red.io.JavaFileInputStream;
import com.jfixby.scarabei.red.io.JavaFileOutputStream;

public class GdxFileSystem extends AbstractFileSystem implements FileSystem {

	public GdxFileSystem () {

	}

	public static final String OS_SEPARATOR = "/";

	@Override
	public GdxFile newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			Err.reportError("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			L.e("file_path", file_path);
			L.e("FileSystem", this.ROOT());
			Err.reportError("Path does not belong to this filesystem: " + file_path);
		}
		return new GdxFile(file_path, this);
	}

	final private String string_path = "GdxAssetsFileSystem";

	@Override
	public String toString () {
		return this.string_path;
	}

	public FileSystem Home () {
		return this;
	}

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

}
