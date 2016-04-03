package com.jfixby.cmns.api.file;

import java.io.IOException;

import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.RelativePath;

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

    /*
     * Example: .extensionIs(".java");
     */
    public boolean extensionIs(String postfix);

    boolean rename(String new_name);

    String getName();

    FileSystem getFileSystem();

    String nameWithoutExtension();

    FileInputStream newInputStream() throws IOException;

    FileOutputStream newOutputStream() throws IOException;

    String readToString() throws IOException;

    ByteArray readBytes() throws IOException;

    void writeBytes(ByteArray bytes) throws IOException;

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

    ChildrenList listSubFolders();

    void writeData(Object object) throws IOException;

    <T> T readData(Class<T> type) throws IOException;

}
