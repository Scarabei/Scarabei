
package com.jfixby.scarabei.aws.android.s3;

import com.jfixby.scarabei.api.file.FileInputStream;
import com.jfixby.scarabei.aws.android.s3.S3File;
import com.jfixby.scarabei.aws.android.s3.S3FileFileInputStreamOperator;
import com.jfixby.scarabei.red.io.AbstractRedInputStream;

class S3FileInputStream extends AbstractRedInputStream<S3FileFileInputStreamOperator> implements FileInputStream {

	private final S3File file;

	public S3FileInputStream (final S3File file) {
		super(new S3FileFileInputStreamOperator(file));
		this.file = file;
	}

	@Override
	public long getFileSize () {
		return this.file.getSize();
	}

}
