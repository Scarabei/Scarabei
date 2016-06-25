
package com.jfixby.red.filesystem;

import java.io.IOException;
import java.io.Serializable;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.FileFilter;
import com.jfixby.cmns.api.file.FileHash;
import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.file.FileSystem;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;

public abstract class AbstractRedFile implements File {
	@Override
	public void checkIsFolder () {
		this.checkExists();
		if (!this.isFolder()) {
			throw new Error("" + this + " is not a folder");
		}
	}

	@Override
	public void checkExists () {
		if (!this.exists()) {
			throw new Error(this + " does not exist.");
		}
	}

	@Override
	public void checkIsFile () {
		this.checkExists();
		if (!this.isFile()) {
			throw new Error(this + " does not exist.");
		}
	}

	@Override
	public void writeData (final Object object) throws IOException {
		final ByteArray data = IO.serialize((Serializable)object);
		this.writeBytes(data);
	}

	@Override
	public File proceed (final RelativePath relativePath) {
		final AbsolutePath<FileSystem> file_path = this.getAbsoluteFilePath().proceed(relativePath);
		return this.getFileSystem().newFile(file_path);
	}

	@Override
	public boolean extensionIs (final String postfix) {
		return this.getName().toLowerCase().endsWith("." + postfix.toLowerCase());
	}

	@Override
	public ChildrenList listSubFolders () {
		final FilesList listFiles = new FilesList();
		final ChildrenList children = this.listChildren();
		for (final File file : children) {
			if (file.isFolder()) {
				listFiles.add(file);
			}
		}

		return listFiles;
	}

	@Override
	public String readToString () throws IOException {
		return JUtils.newString(this.readBytes());
	}

	@Override
	public String readToString (final String encoding) throws IOException {
		return JUtils.newString(this.readBytes(), encoding);
	}

	@Override
	public ByteArray readBytes () throws IOException {
		final FileInputStream is = this.getFileSystem().newFileInputStream(this);
		is.open();
		final ByteArray bytes = is.readAll();
		is.close();
		return bytes;
	}

	@Override
	public void writeBytes (final ByteArray bytes) throws IOException {
		final FileOutputStream os = this.getFileSystem().newFileOutputStream(this);
		os.open();
		os.write(bytes);
		os.close();
	}

	@Override
	public ChildrenList listChildren (final FileFilter filter) {
		return this.listChildren().filterFiles(filter);
	}

	@Override
	public void writeBytes (final byte[] bytes) throws IOException {
		this.writeBytes(JUtils.newByteArray(bytes));
	}

	@Override
	public void writeString (final String bytes) throws IOException {
		this.writeBytes(JUtils.newByteArray(bytes.getBytes()));
	}

	@Override
	public <T> T readData (final Class<T> type) throws IOException {

		final ByteArray bytes = this.readBytes();
		return IO.deserialize(type, bytes);
	}

	@Override
	public FileHash calculateHash () throws IOException {
		return new RedFileHash((this.getAbstractFileSystem()).md5Hex(this));
	}

	private AbstractFileSystem getAbstractFileSystem () {
		return (AbstractFileSystem)this.getFileSystem();
	}

	@Override
	public FileInputStream newInputStream () {
		return this.getFileSystem().newFileInputStream(this);
	}

	@Override
	public FileOutputStream newOutputStream () {
		return this.getFileSystem().newFileOutputStream(this);
	}

	@Override
	public String getExtension () {
		if (this.isFolder()) {
			return null;
		}
		final String name = this.getName().toLowerCase();
		final int index = name.lastIndexOf('.');
		if (index < 0) {
			return null;
		}
		final String ext = name.substring(index + 1);
		return ext;
	}

	@Override
	public File copyTo (final String newFileName) throws IOException {
		final File outputFile = this.parent().child(Debug.checkNull("newFileName", newFileName));
		this.getFileSystem().copyFileToFile(this, outputFile);
		return outputFile;
	}

}
