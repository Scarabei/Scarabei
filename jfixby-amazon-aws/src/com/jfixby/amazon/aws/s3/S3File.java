
package com.jfixby.amazon.aws.s3;

import java.io.IOException;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractRedFile;
import com.jfixby.red.filesystem.FilesList;

public class S3File extends AbstractRedFile implements File {

	final private AbsolutePath<FileSystem> absolute_path;
	private final AWSS3FileSystem fs;
	private final RelativePath relative;
// private S3ObjectInfo objectInfo;

	public S3File (final AbsolutePath<FileSystem> output_file_path, final AWSS3FileSystem file_system) {
		this.absolute_path = output_file_path;
		this.fs = file_system;
		this.relative = this.absolute_path.getRelativePath();
		if (this.fs.index_is_loaded) {
			final Boolean isFile = this.fs.index().isFile(this.relative);
			final S3ObjectInfo objectInfo = file_system.getObjectInfo(this.relative);

			if (isFile == null) {
				if (objectInfo.exists()) {
					this.throwOutdated(output_file_path);
				}
			} else {
				if (isFile == true) {
					if (!objectInfo.exists()) {
						this.throwOutdated(output_file_path);
					}
				}
			}

		}
	}

	private void throwOutdated (final AbsolutePath<FileSystem> output_file_path) {
		this.fs.index().print("index");
		throw new Error("Index is outdated: " + output_file_path);
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath () {
		return this.absolute_path;
	}

	String getGdxInternalPathString () {
		return AWSS3FileSystem.toS3Key(this.absolute_path.getRelativePath());
	}

	@Override
	public boolean isFile () {
		final Boolean isFile = this.fs.index().isFile(this.relative);
		if (isFile == null) {
			return false;
		}
		return isFile;
	}

	@Override
	public boolean isFolder () {
		final Boolean isFile = this.fs.index().isFile(this.relative);
		if (isFile == null) {
			return false;
		}
		return !isFile;
	}

	@Override
	public boolean exists () {
		final Boolean isFile = this.fs.index().isFile(this.relative);
		if (isFile == null) {
			return false;
		}
		// return isFile != null;
		return isFile || true;
	}

	@Override
	public boolean rename (final String new_name) {
		throw new Error("Read-only file system!");
	}

	@Override
	public boolean makeFolder () {
		// java.io.File f = new java.io.File(this.getGdxInternalPathString());
		// return f.mkdirs();
		throw new Error("Read-only file system!");
	}

	@Override
	public boolean delete () {
		// if (this.isFolder()) {
		// this.clearFolder();
		// }
		// java.io.File f = new java.io.File(getGdxInternalPathString());
		//
		// L.d("deleting", f);
		// f.delete();
		throw new Error("Read-only file system!");
	}

	public static String toNativePathString (final String string) {
		return string.replaceAll(RelativePath.SEPARATOR, AWSS3FileSystem.OS_SEPARATOR + AWSS3FileSystem.OS_SEPARATOR);
	}

	@Override
	public ChildrenList listChildren () {
// final FileHandle file = Gdx.files.internal(this.getGdxInternalPathString());

		if (!this.exists()) {
			throw new Error("File does not exist: " + this.absolute_path);
		}
		if (this.isFolder()) {
			final List<String> my_steps = this.relative.steps();
			final FilesList listFiles = new FilesList();
			for (int i = 0; i < this.fs.index().size(); i++) {
				final RelativePath path = this.fs.index().getKeyAt(i);
				final List<String> candidate_steps = path.steps();
				if (my_steps.size() + 1 == candidate_steps.size()) {
					if (path.parent().equals(this.relative)) {
						final AbsolutePath<FileSystem> absolute_file = this.absolute_path.child(candidate_steps.getLast());
						listFiles.add(absolute_file.getMountPoint().newFile(absolute_file));
					}
				}
			}
			return listFiles;
		} else {
			throw new Error("This is not a folder: " + this.absolute_path);
		}
	}

	@Override
	public void clearFolder () {
		throw new Error("Read-only file system!");
	}

	@Override
	public String toString () {
		return "@GdxAssetsFileSystem:>" + RelativePath.SEPARATOR + this.absolute_path.getRelativePath();
	}

	@Override
	public File child (final String child_name) {
		return new S3File(this.getAbsoluteFilePath().child(child_name), this.getFileSystem());
	}

	@Override
	public String getName () {
		// java.io.File f = new java.io.File(this.getGdxInternalPathString());
		return this.absolute_path.getRelativePath().getLastStep();
	}

	@Override
	public AWSS3FileSystem getFileSystem () {
		return this.fs;
	}

	@Override
	public String nameWithoutExtension () {
		final java.io.File file = new java.io.File(this.getGdxInternalPathString());
		final String name = file.getName();
		final int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1) {
			return name;
		}
		return name.substring(0, dotIndex);
	}

	public String toAbsolutePathString () {
		return this.getGdxInternalPathString();
	}

	@Override
	public void writeBytes (final ByteArray bytes) throws IOException {
		throw new IOException("Read-only file system: " + this.getFileSystem());
	}

	@Override
	public void writeString (final String bytes) throws IOException {
		throw new IOException("Read-only file system: " + this.getFileSystem());
	}

	@Override
	public long getSize () {
		return this.toFileHandle().length();
	}

	@Override
	public java.io.File toJavaFile () {
		// FileHandle file =
		// Gdx.files.internal(this.getGdxInternalPathString());
		// java.io.File f = file.file();
		// return f;
		throw new Error("Method is not supported: toJavaFile()");
	}

	@Override
	public File parent () {
		return new S3File(this.absolute_path.parent(), this.fs);
	}

	@Override
	public long lastModified () {
		return this.toFileHandle().lastModified();
	}

	public S3ObjectInfo toFileHandle () {
		final S3ObjectInfo objectInfo = this.fs.getObjectInfo(this.relative);
		if (objectInfo == null) {
			this.throwOutdated(this.absolute_path);
		}
		return objectInfo;
	}

}
