
package com.jfixby.cmns.aws.api;

public interface S3 {

	FileSystemConfig newFileSystemConfig ();

	S3FileSystem newFileSystem (FileSystemConfig specs);

}
