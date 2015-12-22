package com.jfixby.rmi.client.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.log.L;
import com.jfixby.red.io.AbstractRedInputStream;

public class RemoteFileInputStream extends AbstractRedInputStream implements
		FileInputStream {

	public RemoteFileInputStream(RemoteFile input_file) throws IOException {
		super(is(input_file));
	}

	private static InputStream is(RemoteFile input_file)
			throws FileNotFoundException {
		String path_string = input_file.toAbsolutePathString();
		java.io.File file = new java.io.File(path_string);
		if (!file.exists()) {
			L.e("file not found", file);
		}
		return new java.io.FileInputStream(file);
	}

}
