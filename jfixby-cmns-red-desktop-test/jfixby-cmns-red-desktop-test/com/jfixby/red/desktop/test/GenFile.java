package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.red.desktop.DesktopAssembler;

public class GenFile {

	public static void main(String[] args) throws IOException {
		DesktopAssembler.setup();
		File test_file = LocalFileSystem.ApplicationHome().child("testFile.101M");
		test_file.writeBytes(new byte[1024 * 1024 * 101]);

	}
}
