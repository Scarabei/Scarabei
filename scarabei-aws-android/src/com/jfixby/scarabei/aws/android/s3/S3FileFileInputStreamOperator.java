
package com.jfixby.scarabei.aws.android.s3;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.scarabei.api.io.JavaInputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.aws.android.s3.S3File;

class S3FileFileInputStreamOperator implements JavaInputStreamOperator {

	private final S3File file;
	private ByteArrayInputStream bis;

	public S3FileFileInputStreamOperator (final S3File file) {
		this.file = file;
	}

	@Override
	public void closeStream () {
		this.bis = null;
	}

	@Override
	public InputStream getJavaStream () throws IOException {
		if (this.bis == null) {
			this.bis = new ByteArrayInputStream(this.file.info().readBytes(this.file.getFileSystem()));
		}
		return this.bis;
	}

	@Override
	public boolean isReadAllSupported () {
		return true;
	}

	@Override
	public ByteArray readAll () throws IOException {
		return JUtils.newByteArray(this.file.info().readBytes(this.file.getFileSystem()));
	}

}
