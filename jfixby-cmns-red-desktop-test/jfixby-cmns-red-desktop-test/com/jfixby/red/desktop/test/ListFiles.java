
package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.file.LocalFileSystemComponent;

public class ListFiles {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		final LocalFileSystemComponent fileSystem = LocalFileSystem.invoke();

		fileSystem.ROOT().listDirectChildren().print("root");

		fileSystem.ROOT().child("[SHIT]").listDirectChildren()//
			.print("root" + " direct");

		fileSystem.ROOT().child("[SHIT]").listAllChildren()//
			.print("root" + " all");

	}

}
