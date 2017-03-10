
package com.jfixby.scarabei.api.file;

public interface LocalFileSystemComponent extends FileSystem {

	File newFile (java.io.File java_folder);

	File newFile (String java_file_path);

	File ApplicationHome ();

}
