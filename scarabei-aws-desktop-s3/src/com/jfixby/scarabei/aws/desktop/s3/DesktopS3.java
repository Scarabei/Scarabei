
package com.jfixby.scarabei.aws.desktop.s3;

import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.aws.api.s3.S3Component;
import com.jfixby.scarabei.aws.api.s3.S3FileSystem;
import com.jfixby.scarabei.aws.api.s3.S3FileSystemConfig;

public class DesktopS3 implements S3Component {

	@Override
	public S3FileSystemConfig newFileSystemConfig () {
		return new S3FileSystemConfig();
	}

	@Override
	public S3FileSystem newFileSystem (final S3FileSystemConfig specs) {
		return new DesktopS3FileSystem(specs);
	}

	@Override
	public ID AWS_ACCESS_KEY () {
		return Names.newID("com_amazon_aws_ACCESS_KEY");
	}

	@Override
	public ID AWS_SECRET_KEY () {
		return Names.newID("com_amazon_aws_SECRET_KEY");
	}

	@Override
	public ID AWS_REGION_NAME () {
		return Names.newID("com_amazon_aws_REGION_NAME");
	}

	@Override
	public ID BUCKET_NAME () {
		return Names.newID("com_amazon_aws_s3_BUCKET_NAME");
	}

}
