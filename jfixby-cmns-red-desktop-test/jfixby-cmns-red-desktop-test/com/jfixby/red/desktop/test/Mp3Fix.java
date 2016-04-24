
package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.red.desktop.DesktopAssembler;

public class Mp3Fix {

	public static void main (final String[] args) throws IOException {
		DesktopAssembler.setup();
		final File folder = LocalFileSystem.newFile("D:\\[SHIT]\\audio");
		final List<File> not_procesed = folder.listChildren().filter(file -> !file.extensionIs("mp3"));
		Collections.scanCollection(not_procesed, (e, i) -> e.rename(e.nameWithoutExtension() + ".mp3"));
		not_procesed.print("renamed");
	}
}
