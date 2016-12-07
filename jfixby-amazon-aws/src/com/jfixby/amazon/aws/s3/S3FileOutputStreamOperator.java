
package com.jfixby.amazon.aws.s3;

import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.io.JavaOutputStreamOperator;
import com.jfixby.cmns.api.java.ByteArray;

class S3FileOutputStreamOperator implements JavaOutputStreamOperator {
	private BufferOutputStream os;
	private final S3File v_file;
	private final boolean append;

	public S3FileOutputStreamOperator (final S3File output_file, final boolean append) {
		this.v_file = output_file;
		this.append = append;
	}

	@Override
	public void closeStream () {
		if (this.os == null) {
			return;
		}
		this.os.close();
		final ByteArray bytes = this.os.getBytes();
		try {
			this.v_file.writeBytes(bytes);
		} catch (final IOException e) {
			e.printStackTrace();
		}
		this.os = null;
	}

	@Override
	public OutputStream getJavaStream () throws IOException {
		if (this.os == null) {
			this.os = IO.newBufferOutputStream();
			this.os.open();
			if (this.append) {
				Err.reportError("append not supported yet");
			}
		}
		return this.os.toJavaOutputStream();
	}

	@Override
	public boolean isBulkWriteSupported () {
		return true;
	}

	@Override
	public void writeAll (final ByteArray bytes) throws IOException {
		if (this.append) {
			Err.reportError("append not supported yet");
		}
		this.v_file.writeBytes(bytes);
	}
}
