
package com.jfixby.amazon.aws.s3;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.red.io.AbstractRedInputStream;

public class S3FileInputStream extends AbstractRedInputStream<S3FileFileInputStreamOperator> implements FileInputStream {

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
