
package com.jfixby.scarabei.rmi.test;

import java.io.IOException;

import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.rmi.server.files.RMIFileSystemFactory;
import com.jfixby.scarabei.rmi.server.files.RMIFileSystemServer;
import com.jfixby.scarabei.rmi.server.files.RMIFileSystemServerConfig;

public class RMIFileSystemServerTest {

	public static void main (final String[] args) throws IOException {

		final RMIFileSystemServerConfig config = new RMIFileSystemServerConfig();
		config.setPort(16000);
		config.setMailBoxName("remote-filesystem");
		final File server_root = LocalFileSystem.ApplicationHome().child("server-root");
		config.setServerRootFolder(server_root);
		final RMIFileSystemServer server = RMIFileSystemFactory.newServerInstance(config);
		server.start();

	}

}
