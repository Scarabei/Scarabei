package com.jfixby.cmns.api.filesystem;

import java.io.IOException;

import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.cmns.api.path.ChildrenList;
import com.jfixby.cmns.api.path.RelativePath;

public interface File {

	AbsolutePath<FileSystem> getAbsoluteFilePath();

	boolean delete();

	boolean isFolder();

	boolean isFile();

	void clearFolder();

	public FileHash calculateHash() throws IOException;

	ChildrenList listChildren();

	File child(String child_name);

	boolean exists();

	boolean makeFolder();

	boolean rename(String new_name);

	String getName();

	FileSystem getFileSystem();

	String nameWithoutExtension();

	FileInputStream newInputStream() throws IOException;

	FileOutputStream newOutputStream() throws IOException;

	String readToString() throws IOException;

	byte[] readBytes() throws IOException;

	void writeBytes(byte[] bytes) throws IOException;

	long getSize();

	java.io.File toJavaFile();

	File parent();

	void writeString(String data) throws IOException;

	long lastModified();

	void checkIsFolder();

	void checkExists();

	void checkIsFile();

	File proceed(RelativePath relative);

}
