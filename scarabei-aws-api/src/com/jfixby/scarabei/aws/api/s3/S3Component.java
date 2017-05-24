
package com.jfixby.scarabei.aws.api.s3;

public interface S3Component {

	S3FileSystemConfig newFileSystemConfig ();

	S3FileSystem newFileSystem (S3FileSystemConfig specs);

}
