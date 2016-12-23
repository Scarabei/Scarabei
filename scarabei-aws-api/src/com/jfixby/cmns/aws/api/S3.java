
package com.jfixby.cmns.aws.api;

public interface S3 {

	S3FileSystemConfig newFileSystemConfig ();

	S3FileSystem newFileSystem (S3FileSystemConfig specs);

}
