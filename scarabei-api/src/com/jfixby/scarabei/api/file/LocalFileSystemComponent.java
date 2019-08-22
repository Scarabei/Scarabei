
package com.jfixby.scarabei.api.file;

public interface LocalFileSystemComponent extends FileSystem {

	LocalFile newFile (java.io.File java_folder);

	LocalFile newFile (String java_file_path);

	LocalFile ApplicationHome ();

}
