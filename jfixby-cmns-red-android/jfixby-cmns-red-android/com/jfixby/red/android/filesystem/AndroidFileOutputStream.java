
package com.jfixby.red.android.filesystem;

import java.io.BufferedOutputStream;
import java.io.IOException;

import com.jfixby.red.io.AbstractRedOutputStream;

public class AndroidFileOutputStream extends AbstractRedOutputStream {

	public AndroidFileOutputStream (AndroidFile output_file) throws IOException {
		super(os(output_file));
	}

	private static BufferedOutputStream os (AndroidFile output_file) throws IOException {
		output_file.parent().makeFolder();
		String path_string = output_file.toAbsolutePathString();
		java.io.File file = new java.io.File(path_string);

		BufferedOutputStream os = new BufferedOutputStream(new java.io.FileOutputStream(file));
		return os;
	}

}
