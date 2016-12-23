
package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.file.ChildrenList;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;

public class Mp3Fix {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();
		final File folder = LocalFileSystem.newFile("D:\\[SHIT]\\audio");
		final ChildrenList not_procesed = folder.listDirectChildren(file -> !file.extensionIs("mp3"));
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
