
package com.jfixby.scarabei.rmi.test;

import java.io.IOException;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.rmi.client.files.RMIFileSystem;
import com.jfixby.scarabei.rmi.client.files.RMIFileSystemConfig;

public class RMIFileSystemClientTest {
	public static void main (final String[] args) throws IOException {

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
