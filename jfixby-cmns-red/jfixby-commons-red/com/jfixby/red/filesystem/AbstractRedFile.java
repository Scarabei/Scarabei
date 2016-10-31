
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
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;

public abstract class AbstractRedFile implements File {

	@Override
	public void clearFolder () {
		if (this.isFolder()) {
			final ChildrenList children = this.listDirectChildren();
			for (int i = 0; i < children.size(); i++) {
				// WinFile file = new WinFile(child);
				final File child = children.getElementAt(i);
				child.delete();
				// L.d("deleting", child.getAbsoluteFilePath());
			}
		} else {
			L.e("Unable to clear", this.getAbsoluteFilePath());
			L.e("       this is not a folder.");
		}
	}

	@Override
	public int hashCode () {
		return this.getAbsoluteFilePath().hashCode();
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final AbstractRedFile other = (AbstractRedFile)obj;

		return this.getAbsoluteFilePath().equals(other.getAbsoluteFilePath());
	}

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
		final String name = this.getName().toLowerCase();
		final boolean result = name.endsWith("." + postfix.toLowerCase());
		return result;
	}

	@Override
	public ChildrenList listSubFolders () {
		final FilesList listFiles = new FilesList();
		final ChildrenList children = this.listDirectChildren();
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
		this.writeBytes(bytes, false);
	}

	@Override
	public void writeBytes (final ByteArray bytes, final boolean append) throws IOException {
		final FileOutputStream os = this.getFileSystem().newFileOutputStream(this, append);
		os.open();
		os.write(bytes);
		os.close();
	}

	@Override
	public ChildrenList listChildren (final FileFilter filter) {
		return this.listDirectChildren().filterFiles(filter);
	}

	@Override
	public void writeBytes (final byte[] bytes) throws IOException {
		this.writeBytes(JUtils.newByteArray(bytes), false);
	}

	@Override
	public void writeString (final String bytes) throws IOException {
		this.writeBytes(JUtils.newByteArray(bytes.getBytes()), false);
	}

	@Override
	public void writeString (final String bytes, final boolean append) throws IOException {
		this.writeBytes(JUtils.newByteArray(bytes.getBytes()), append);
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
	public FileOutputStream newOutputStream (final boolean append) {
		return this.getFileSystem().newFileOutputStream(this, append);
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
