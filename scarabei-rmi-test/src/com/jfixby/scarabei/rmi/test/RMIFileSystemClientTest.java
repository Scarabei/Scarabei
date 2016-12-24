
package com.jfixby.scarabei.rmi.test;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.red.debug.RedDebug;
import com.jfixby.scarabei.red.desktop.collections.DesktopCollections;
import com.jfixby.scarabei.red.err.RedError;
import com.jfixby.scarabei.red.io.RedIO;
import com.jfixby.scarabei.red.log.SimpleLogger;
import com.jfixby.scarabei.red.util.RedJUtils;
import com.jfixby.scarabei.rmi.client.files.RMIFileSystem;
import com.jfixby.scarabei.rmi.client.files.RMIFileSystemConfig;

public class RMIFileSystemClientTest {
	public static void main (final String[] args) throws IOException {
		L.installComponent(new SimpleLogger());
		JUtils.installComponent(new RedJUtils());
		Collections.installComponent(new DesktopCollections());

		IO.installComponent(new RedIO());
		Err.installComponent(new RedError());
		Debug.installComponent(new RedDebug());

		final RMIFileSystemConfig config = new RMIFileSystemConfig();
		config.setRemoteHost("127.0.0.1");
		config.setRemotePort(16000);
		config.setRemoteBox("remote-filesystem");

		final RMIFileSystem remote_file_system = new RMIFileSystem(config);
		remote_file_system.ping();

		remote_file_system.ROOT().listDirectChildren().print("scan root");
		final File A = remote_file_system.ROOT().child("a");
		final File B = remote_file_system.ROOT().child("b");
		A.listDirectChildren().print("A");

		remote_file_system.copyFolderContentsToFolder(A, B);

	}
}
