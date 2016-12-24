package com.jfixby.scarabei.rmi.test;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.LocalFileSystem;
import com.jfixby.scarabei.api.io.IO;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.red.debug.RedDebug;
import com.jfixby.scarabei.red.desktop.collections.DesktopCollections;
import com.jfixby.scarabei.red.desktop.filesystem.unix.UnixFileSystem;
import com.jfixby.scarabei.red.err.RedError;
import com.jfixby.scarabei.red.io.RedIO;
import com.jfixby.scarabei.red.log.SimpleLogger;
import com.jfixby.scarabei.red.util.RedJUtils;
import com.jfixby.scarabei.rmi.server.files.RMIFileSystemFactory;
import com.jfixby.scarabei.rmi.server.files.RMIFileSystemServer;
import com.jfixby.scarabei.rmi.server.files.RMIFileSystemServerConfig;

public class RMIFileSystemServerTest {

	public static void main(String[] args) throws IOException {
		L.installComponent(new SimpleLogger());
		JUtils.installComponent(new RedJUtils());
		Collections.installComponent(new DesktopCollections());
		
		IO.installComponent(new RedIO());
		Err.installComponent(new RedError());
		Debug.installComponent(new RedDebug());

		LocalFileSystem.installComponent(new UnixFileSystem());

		RMIFileSystemServerConfig config = new RMIFileSystemServerConfig();
		config.setPort(16000);
		config.setMailBoxName("remote-filesystem");
		File server_root = LocalFileSystem.ApplicationHome().child("server-root");
		config.setServerRootFolder(server_root);
		RMIFileSystemServer server = RMIFileSystemFactory.newServerInstance(config);
		server.start();

	}

}
