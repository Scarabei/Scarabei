
package com.jfixby.scarabei.red.filesystem;

import java.io.IOException;
import java.io.Serializable;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileFilter;
import com.jfixby.scarabei.api.file.FileHash;
import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.file.FileSystem;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;

public abstract class AbstractRedFile implements File {

	@Override
	public void clearFolder () throws IOException {
		if (this.isFolder()) {
			final FilesList children = this.listDirectChildren();
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
	public void checkIsFolder () throws IOException {
		this.checkExists();
		if (!this.isFolder()) {
			throw new IOException("" + this + " is not a folder");
		}
	}

	@Override
	public void checkExists () throws IOException {
		if (!this.exists()) {
			throw new IOException(this + " does not exist.");
		}
	}

	@Override
	public void checkIsFile () throws IOException {
		this.checkExists();
		if (!this.isFile()) {
			throw new IOException(this + " does not exist.");
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
	public FilesList listSubFolders () throws IOException {
		final RedFilesList listFiles = new RedFilesList();
		final FilesList children = this.listDirectChildren();
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
		final ByteArray bytes;
		is.open();
		try {
			bytes = is.readAll();
			return bytes;
		} catch (final IOException e) {
			throw e;
		} finally {
			is.close();
		}
	}

	@Override
	public void writeBytes (final ByteArray bytes) throws IOException {
		this.writeBytes(bytes, false);
	}

	@Override
	public void writeBytes (final ByteArray bytes, final boolean append) throws IOException {
		final FileOutputStream os = this.getFileSystem().newFileOutputStream(this, append);
		os.open();
		try {
			os.write(bytes);
		} catch (final IOException e) {
			throw e;
		} finally {
			os.close();
		}
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
	final public String toString () {
		return this.getAbsoluteFilePath() + "";
	}

	@Override
	public <T> T readData (final Class<T> type) throws IOException {
		final ByteArray bytes = this.readBytes();
		return IO.deserialize(type, bytes);
	}

	@Override
	public FileHash calculateHash () throws IOException {
		return ((this.getAbstractFileSystem()).md5Hex(this));
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
	public String getExtension () throws IOException {
		if (this.isFolder()) {
			return "";
		}
		final String name = this.getName().toLowerCase();
		final int index = name.lastIndexOf('.');
		if (index < 0) {
			return "";
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

	@Override
	final public FilesList listDirectChildren (final FileFilter filter) throws IOException {
		return this.listDirectChildren().filter(filter);// ugly hack
	}

	@Override
	final public FilesList listAllChildren (final FileFilter filter) throws IOException {
		return this.listAllChildren().filter(filter);// ugly hack
	}

	@Override
	public boolean tryToClearFolder () {
		try {
			this.clearFolder();
			return true;
		} catch (final IOException e) {
			L.e(e);
			return false;
		}
	}

	@Override
	public FilesList listAllChildren () throws IOException {
		final List<File> filesQueue = Collections.newList();
		filesQueue.add(this);
		final RedFilesList result = new RedFilesList();
		collectChildren(filesQueue, result, false);

		return result;

	}

	private static final boolean DIRECT_CHILDREN = true;
	private static final boolean ALL_CHILDREN = !DIRECT_CHILDREN;

	static private void collectChildren (final List<File> filesQueue, final RedFilesList result, final boolean directFlag)
		throws IOException {
		while (filesQueue.size() > 0) {
			final File nextfile = filesQueue.removeElementAt(0);

			if (nextfile.isFolder()) {

				final FilesList files = nextfile.listDirectChildren();

				for (int i = 0; i < files.size(); i++) {
					final File child = files.getElementAt(i);
					result.add(child);
					if (directFlag == ALL_CHILDREN) {

						if (child.isFolder()) {
							filesQueue.add(child);
						}
					} else {

					}
				}

			} else {
				Err.reportError("This is not a folder: " + nextfile.getAbsoluteFilePath());
			}

		}
	}

}
