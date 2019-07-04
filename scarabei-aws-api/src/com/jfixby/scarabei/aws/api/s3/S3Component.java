
package com.jfixby.scarabei.aws.api.s3;

import com.jfixby.scarabei.api.names.ID;

public interface S3Component {

	S3FileSystemConfig newFileSystemConfig ();

	S3FileSystem newFileSystem (S3FileSystemConfig specs);

	ID AWS_ACCESS_KEY ();

	ID AWS_SECRET_KEY ();

	ID AWS_REGION_NAME ();

	ID BUCKET_NAME ();

}
