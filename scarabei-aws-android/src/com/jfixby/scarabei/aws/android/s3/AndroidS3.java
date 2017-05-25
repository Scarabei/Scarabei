
package com.jfixby.scarabei.aws.android.s3;

import com.jfixby.scarabei.aws.android.s3.AndroidS3FileSystem;
import com.jfixby.scarabei.aws.android.s3.AndroidS3FileSystemConfig;
import com.jfixby.scarabei.aws.api.s3.S3Component;
import com.jfixby.scarabei.aws.api.s3.S3FileSystem;
import com.jfixby.scarabei.aws.api.s3.S3FileSystemConfig;

public class AndroidS3 implements S3Component {

	@Override
	public S3FileSystemConfig newFileSystemConfig () {
		return new AndroidS3FileSystemConfig();
	}

	@Override
	public S3FileSystem newFileSystem (final S3FileSystemConfig specs) {
		return new AndroidS3FileSystem(specs);
	}

}
