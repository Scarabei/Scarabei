
package com.jfixby.amazon.aws.s3;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.red.io.AbstractRedOutputStream;

public class S3FileOutputStream extends AbstractRedOutputStream<S3FileOutputStreamOperator> implements FileOutputStream {

	public S3FileOutputStream (final S3File file, final boolean append) {
		super(new S3FileOutputStreamOperator(file, append));
	}

}
