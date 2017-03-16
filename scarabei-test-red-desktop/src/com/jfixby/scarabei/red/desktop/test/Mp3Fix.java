
package com.jfixby.scarabei.red.desktop.test;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FilesList;
import com.jfixby.scarabei.api.file.LocalFileSystem;

public class Mp3Fix {

	public static void main (final String[] args) throws IOException {
		ScarabeiDesktop.deploy();
		final File folder = LocalFileSystem.newFile("D:\\[SHIT]\\audio");
		final FilesList not_procesed = folder.listDirectChildren(file -> !file.extensionIs("mp3"));
		Collections.scanCollection(not_procesed, (e, i) -> {
			try {
				e.rename(e.nameWithoutExtension() + ".mp3");
			} catch (final IOException e1) {
				e1.printStackTrace();
			}
		});
		not_procesed.print("renamed");
	}
}
