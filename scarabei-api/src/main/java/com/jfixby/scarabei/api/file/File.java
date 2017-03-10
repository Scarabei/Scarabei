
package com.jfixby.scarabei.api.file;

import java.io.IOException;

import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.RelativePath;

public interface File {
	java.io.File toJavaFile ();

	AbsolutePath<FileSystem> getAbsoluteFilePath ();

	boolean delete () throws IOException;

	boolean isFolder () throws IOException;

	boolean isFile () throws IOException;

	void clearFolder () throws IOException;

	public FileHash calculateHash () throws IOException;

	public FilesList listDirectChildren () throws IOException;

	public FilesList listDirectChildren (FileFilter filter) throws IOException;

	public FilesList listAllChildren () throws IOException;

	FilesList listAllChildren (FileFilter pdfFileFilter) throws IOException;

	File child (String child_name);

	boolean exists () throws IOException;

	boolean makeFolder () throws IOException;

	/*
	 * Examples: .extensionIs("java"); .extensionIs("txt"); .extensionIs("jar");
	 */
	public boolean extensionIs (String postfix);

	public String getExtension () throws IOException;

	boolean rename (String new_name) throws IOException;

	String getName ();

	FileSystem getFileSystem ();

	String nameWithoutExtension ();

	FileInputStream newInputStream ();

	FileOutputStream newOutputStream ();

	FileOutputStream newOutputStream (boolean append);

	String readToString () throws IOException;

	String readToString (String encoding) throws IOException;

	ByteArray readBytes () throws IOException;

	void writeBytes (ByteArray bytes) throws IOException;

	void writeBytes (ByteArray bytes, boolean append) throws IOException;

	void writeBytes (byte[] bytes) throws IOException;

	long getSize () throws IOException;

	File parent ();

	void writeString (String data) throws IOException;

	void writeString (String string, boolean append) throws IOException;

	long lastModified () throws IOException;

	void checkIsFolder () throws IOException;

	void checkExists () throws IOException;

	void checkIsFile () throws IOException;

	File proceed (RelativePath relative);

	FilesList listSubFolders () throws IOException;

	void writeData (Object object) throws IOException;

	<T> T readData (Class<T> type) throws IOException;

	File copyTo (String newFileName) throws IOException;

	boolean tryToClearFolder ();

}
