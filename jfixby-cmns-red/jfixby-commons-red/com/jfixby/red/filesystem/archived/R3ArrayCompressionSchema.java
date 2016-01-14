package com.jfixby.red.filesystem.archived;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.packing.CompressionSchema;
import com.jfixby.cmns.api.io.OutputStream;

public class R3ArrayCompressionSchema implements CompressionSchema {

	public static final String SCHEMA_NAME = "R3.Array";

	@Override
	public String getName() {
		return SCHEMA_NAME;
	}

	@Override
	public void pack(File input, OutputStream os) throws IOException {
		java.io.OutputStream jos = os.toJavaOutputStream();
		jos.write(":\n".getBytes());
		jos.write("data data data data data data data data data data".getBytes());
	}

}
