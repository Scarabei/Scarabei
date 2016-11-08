
package com.jfixby.cmns.api.file;

import java.io.IOException;

import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;

public interface File {

	AbsolutePath<FileSystem> getAbsoluteFilePath ();

	boolean delete () throws IOException;

	boolean isFolder () throws IOException;

	boolean isFile () throws IOException;

	void clearFolder () throws IOException;

	public FileHash calculateHash () throws IOException;

	public ChildrenList listDirectChildren () throws IOException;

	public ChildrenList listDirectChildren (FileFilter filter) throws IOException;

	public ChildrenList listAllChildren () throws IOException;

	File child (String child_name);

	boolean exists () throws IOException;

	boolean makeFolder ();

	/*
	 * Examples: .extensionIs("java"); .extensionIs("txt"); .extensionIs("jar");
	 */
	public boolean extensionIs (String postfix);

	public String getExtension () throws IOException;

	boolean rename (String new_name);

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

	java.io.File toJavaFile ();

	File parent ();

	void writeString (String data) throws IOException;

	void writeString (String string, boolean append) throws IOException;

	long lastModified () throws IOException;

	void checkIsFolder () throws IOException;

	void checkExists () throws IOException;

	void checkIsFile () throws IOException;

	File proceed (RelativePath relative);

	ChildrenList listSubFolders () throws IOException;

	void writeData (Object object) throws IOException;

	<T> T readData (Class<T> type) throws IOException;

	File copyTo (String newFileName) throws IOException;

}
