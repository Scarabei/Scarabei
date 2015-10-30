package com.jfixby.red.desktop.filesystem.unix;

import java.io.BufferedOutputStream;
import java.io.IOException;

import com.jfixby.red.io.AbstractRedOutputStream;

public class UnixFileOutputStream extends AbstractRedOutputStream {

	public UnixFileOutputStream(UnixFile output_file) throws IOException {
		super(os(output_file));
	}

	private static BufferedOutputStream os(UnixFile output_file) throws IOException {
		String path_string = output_file.toAbsolutePathString();
		java.io.File file = new java.io.File(path_string);

		BufferedOutputStream os = new BufferedOutputStream(
				new java.io.FileOutputStream(file));
		return os;
	}

}
