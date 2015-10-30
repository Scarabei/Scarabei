package com.jfixby.cmns.api.filesystem;

import com.jfixby.cmns.api.path.AbsolutePath;

public interface LocalFileSystemComponent extends FileSystem {

	File newFile(java.io.File java_folder);

	File newFile(String java_file_path);

	java.io.File toJavaFile(File file);

	String toAbsolutePathString(AbsolutePath<FileSystem> downloaded_file);

	File ApplicationHome();

	File WorkspaceFolder();

}