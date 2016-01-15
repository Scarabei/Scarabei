package com.jfixby.cmns.api.file;

public interface FileSystemSandBoxComponent {

	FileSystem wrap(String sandbox_name, File content_folder);

}
