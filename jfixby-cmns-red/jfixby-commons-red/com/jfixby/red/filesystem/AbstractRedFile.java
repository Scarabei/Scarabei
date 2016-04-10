
package com.jfixby.red.filesystem;

import java.io.IOException;
import java.io.Serializable;

import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
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
		checkExists();
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
		checkExists();
		if (!this.isFile()) {
			throw new Error(this + " does not exist.");
		}
	}

	@Override
	public void writeData (Object object) throws IOException {
		ByteArray data = IO.serialize((Serializable)object);
		this.writeBytes(data);
	}

	@Override
	public File proceed (RelativePath relativePath) {
		AbsolutePath<FileSystem> file_path = this.getAbsoluteFilePath().proceed(relativePath);
		return this.getFileSystem().newFile(file_path);
	}

	@Override
	public boolean extensionIs (final String postfix) {
		return this.getName().toLowerCase().endsWith("." + postfix.toLowerCase());
	}

	@Override
	public ChildrenList listSubFolders () {
		FilesList listFiles = new FilesList();
		ChildrenList children = this.listChildren();
		for (File file : children) {
			if (file.isFolder()) {
				listFiles.add(file);
			}
		}

		return listFiles;
	}

	@Override
	public String readToString () throws IOException {
		FileInputStream is = this.newInputStream();
		ByteArray data = is.readAll();
		is.close();
		return JUtils.newString(data);
	}

	@Override
	public ByteArray readBytes () throws IOException {
		FileInputStream is = this.getFileSystem().newFileInputStream(this);
		ByteArray bytes = is.readAll();
		is.close();
		return bytes;
	}

	@Override
	public void writeBytes (ByteArray bytes) throws IOException {
		FileOutputStream os = this.getFileSystem().newFileOutputStream(this);
		os.write(bytes);
		os.close();
	}

	@Override
	public void writeBytes (byte[] bytes) throws IOException {
		this.writeBytes(JUtils.newByteArray(bytes));
	}

	@Override
	public void writeString (String bytes) throws IOException {
		this.writeBytes(JUtils.newByteArray(bytes.getBytes()));
	}

	@Override
	public <T> T readData (Class<T> type) throws IOException {

		ByteArray bytes = this.readBytes();
		return IO.deserialize(type, bytes);
	}

}
