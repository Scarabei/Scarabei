
package com.jfixby.amazon.aws.s3;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileHash;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;
import com.jfixby.red.filesystem.AbstractRedFile;
import com.jfixby.red.filesystem.FilesList;
import com.jfixby.red.filesystem.RedFileHash;

class S3File extends AbstractRedFile implements File {

	final private AbsolutePath<FileSystem> absolute_path;
	private final AWSS3FileSystem fs;
	private final RelativePath relative;
// private S3ObjectInfo objectInfo;

	public S3File (final AbsolutePath<FileSystem> output_file_path, final AWSS3FileSystem file_system) {
		this.absolute_path = output_file_path;
		this.fs = file_system;
		this.relative = this.absolute_path.getRelativePath();
	}

	@Override
	public AbsolutePath<FileSystem> getAbsoluteFilePath () {
		return this.absolute_path;
	}

	@Override
	public boolean isFile () {
		if (this.relative.isRoot()) {
			return false;
		}
		return this.info().isFile();
	}

	@Override
	public boolean isFolder () {
		if (this.relative.isRoot()) {
			return true;
		}
		return this.info().isFolder();
	}

	@Override
	public boolean exists () {
		if (this.relative.isRoot()) {
			return true;
		}
		return this.info() != null;
	}

	@Override
	public boolean rename (final String new_name) {
		Err.reportError("Read-only file system!");
		return false;
	}

	@Override
	public boolean makeFolder () {
		return this.fs.createS3Folder(this.relative);
	}

	@Override
	public boolean delete () {
		if (this.isFolder()) {

			this.clearFolder();
			this.fs.deleteS3Folder(this.relative);
		} else {
			this.fs.deleteS3File(this.relative);
		}
		L.d("delete", this);
		return true;
	}

	public static String toNativePathString (final String string) {
		return string.replaceAll(RelativePath.SEPARATOR, AWSS3FileSystem.OS_SEPARATOR + AWSS3FileSystem.OS_SEPARATOR);
	}

	@Override
	public ChildrenList listDirectChildren () {
// final FileHandle file = Gdx.files.internal(this.getGdxInternalPathString());

		if (!this.exists()) {
			Err.reportError("File does not exist: " + this.absolute_path);
		}
		if (this.isFolder()) {
			final FilesList listFiles = new FilesList();
			final List<String> subfolders = this.info().listDirectSubfolders();
			final List<String> files = this.info().listDirectChildFiles();

			Collections.scanCollection(subfolders,
				(folderName, index) -> listFiles.add(this.fs.newFile(this.getAbsoluteFilePath().child(folderName))));

			Collections.scanCollection(files,
				(fileName, index) -> listFiles.add(this.fs.newFile(this.getAbsoluteFilePath().child(fileName))));

			return listFiles;
		} else {
			Err.reportError("This is not a folder: " + this.absolute_path);
		}
		return null;
	}

// @Override
// public String toString () {
// return "@" + this.fs.toString() + ":>" + RelativePath.SEPARATOR + this.absolute_path.getRelativePath();
// }

	@Override
	public File child (final String child_name) {
		return new S3File(this.getAbsoluteFilePath().child(child_name), this.getFileSystem());
	}

	@Override
	public String getName () {
		return this.absolute_path.getRelativePath().getLastStep();
	}

	@Override
	public AWSS3FileSystem getFileSystem () {
		return this.fs;
	}

	@Override
	public String nameWithoutExtension () {
		final String name = this.getName();
		final int dotIndex = name.lastIndexOf('.');
		if (dotIndex == -1) {
			return name;
		}
		return name.substring(0, dotIndex);
	}

	@Override
	public FilesList listAllChildren () {
		final S3ObjectInfo info = this.fs.listAllS3Keys(this.relative);
		final FilesList result = new FilesList();

		Collections.scanCollection(info.allChildren,
			(e, i) -> result.add(this.fs.newFile(JUtils.newAbsolutePath(this.fs, e.path))));

		return result;
	}

	@Override
	final public void clearFolder () {
		if (this.isFolder()) {
			final S3ObjectInfo info = this.fs.listAllS3Keys(this.relative);
			Collections.scanCollection(info.allChildren, (e, i) -> this.fs.deleteS3Object(e.s3Key));
		} else {
			L.e("Unable to clear", this.getAbsoluteFilePath());
			L.e("       this is not a folder.");
		}
	}

	@Override
	public void writeBytes (final ByteArray bytes) throws IOException {
		this.fs.writeData(this.relative, bytes);
	}

	@Override
	public long getSize () {
		return this.info().length();
	}

	@Override
	public java.io.File toJavaFile () {
		Err.reportError("Method is not supported: toJavaFile()");
		return null;
	}

	@Override
	public File parent () {
		return new S3File(this.absolute_path.parent(), this.fs);
	}

	@Override
	public long lastModified () {
		return this.info().lastModified();
	}

	@Override
	public FileHash calculateHash () throws IOException {
		return new RedFileHash(this.info().md5());
	}

	public S3ObjectInfo info () {
		return this.fs.retrieveInfo(this.relative);
	}

}
