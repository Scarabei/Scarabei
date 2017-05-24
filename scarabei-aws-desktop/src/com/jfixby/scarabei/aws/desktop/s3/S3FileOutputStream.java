
package com.jfixby.scarabei.aws.desktop.s3;

import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.red.io.AbstractRedOutputStream;

class S3FileOutputStream extends AbstractRedOutputStream<S3FileOutputStreamOperator> implements FileOutputStream {

	public S3FileOutputStream (final S3File file, final boolean append) {
		super(new S3FileOutputStreamOperator(file, append));
	}

}
