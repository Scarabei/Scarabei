package com.jfixby.cmns.api.filesystem;

public interface FileSystemSandBoxComponent {

	FileSystem wrap(String sandbox_name, File content_folder);

}
