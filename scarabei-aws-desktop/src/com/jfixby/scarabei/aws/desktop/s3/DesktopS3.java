
package com.jfixby.scarabei.aws.desktop.s3;

import com.jfixby.scarabei.aws.api.s3.S3Component;
import com.jfixby.scarabei.aws.api.s3.S3FileSystem;
import com.jfixby.scarabei.aws.api.s3.S3FileSystemConfig;

public class DesktopS3 implements S3Component {

	@Override
	public S3FileSystemConfig newFileSystemConfig () {
		return new DesktopS3FileSystemConfig();
	}

	@Override
	public S3FileSystem newFileSystem (final S3FileSystemConfig specs) {
		return new DesktopS3FileSystem(specs);
	}

}
