package com.jfixby.red.desktop.filesystem.win;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.jfixby.red.io.AbstractRedOutputStream;

public class WinFileOutputStream extends AbstractRedOutputStream {

    public WinFileOutputStream(WinFile output_file) throws IOException {
	super(os(output_file));
    }

    private static OutputStream os(WinFile output_file) throws IOException {
	output_file.parent().makeFolder();
	String path_string = output_file.toAbsolutePathString();
	java.io.File file = new java.io.File(path_string);

	FileOutputStream fos = new java.io.FileOutputStream(file);
	// BufferedOutputStream os = new BufferedOutputStream(
	// fos);
	return fos;
    }

}
