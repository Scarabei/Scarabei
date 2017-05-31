
package com.jfixby.r3.fokker.filesystem.assets.fs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.scarabei.api.io.JavaInputStreamOperator;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.JUtils;

public class GdxAssetFileFileInputStreamOperator implements JavaInputStreamOperator {

	private final GdxAssetFile file;
	private ByteArrayInputStream bis;

	public GdxAssetFileFileInputStreamOperator (final GdxAssetFile file) {
		this.file = file;
	}

	@Override
	public void closeStream () {
		this.bis = null;
	}

	@Override
	public InputStream getJavaStream () throws IOException {
		if (this.bis == null) {
			this.bis = new ByteArrayInputStream(this.file.toFileHandle().readBytes());
		}
		return this.bis;
	}

	@Override
	public boolean isReadAllSupported () {
		return true;
	}

	@Override
	public ByteArray readAll () throws IOException {
		return JUtils.newByteArray(this.file.toFileHandle().readBytes());
	}

}
