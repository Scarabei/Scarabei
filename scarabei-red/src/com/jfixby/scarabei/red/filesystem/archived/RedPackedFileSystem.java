
package com.jfixby.scarabei.red.filesystem.archived;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.packing.CompressionSchema;
import com.jfixby.scarabei.api.file.packing.PackedFileSystem;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.red.filesystem.AbstractFileSystem;

public class RedPackedFileSystem extends AbstractFileSystem implements FileSystem, PackedFileSystem {

	public static final String OS_SEPARATOR = "/";

	final private PackedFileSystemContent content;

	public RedPackedFileSystem (final CompressionSchema schema, final File archive) {
		this.content = new PackedFileSystemContent(schema, archive);
	}

	@Override
	public PackedFile newFile (final AbsolutePath<FileSystem> file_path) {
		if (file_path == null) {
			Err.reportError("Filepath is null.");
		}
		if (file_path.getMountPoint() != this) {
			Err.reportError("Path does not belong to this filesystem: " + file_path);
		}
		return new PackedFile(this, file_path);
	}

	@Override
	public FileOutputStream newFileOutputStream (final File output_file, final boolean append) {
		Err.reportError("Not supported (yet?)");
		return null;
	}

	@Override
	public FileInputStream newFileInputStream (final File input_file) {
		Err.reportError("Not supported (yet?)");
		return null;
	}

	final private String name = "PackedFileSystem";

	public static String toNativePathString (final String string) {
		return string;
	}

	@Override
	public String toString () {
		return this.name;
	}

	public PackedFileSystemContent getContent () {
		return this.content;
	}

	public String getName () {
		return this.name;
	}

}
