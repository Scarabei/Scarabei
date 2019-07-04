
package com.jfixby.scarabei.aws.android.s3;

import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.aws.api.s3.S3Component;
import com.jfixby.scarabei.aws.api.s3.S3FileSystem;
import com.jfixby.scarabei.aws.api.s3.S3FileSystemConfig;

public class AndroidS3 implements S3Component {

	@Override
	public S3FileSystemConfig newFileSystemConfig () {
		return new S3FileSystemConfig();
	}

	@Override
	public S3FileSystem newFileSystem (final S3FileSystemConfig specs) {
		return new AndroidS3FileSystem(specs);
	}

	@Override
	public ID BUCKET_NAME () {
		return Names.newID("com_amazon_aws_s3_BUCKET_NAME");
	}

}
