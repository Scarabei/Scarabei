package com.jfixby.rmi.test;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.red.debug.RedDebug;
import com.jfixby.red.desktop.collections.DesktopCollections;
import com.jfixby.red.desktop.filesystem.unix.UnixFileSystem;
import com.jfixby.red.desktop.log.DesktopLogger;
import com.jfixby.red.desktop.sys.DesktopSystem;
import com.jfixby.red.err.RedError;
import com.jfixby.red.io.RedIO;
import com.jfixby.red.util.RedJUtils;
import com.jfixby.rmi.server.files.RMIFileSystemFactory;
import com.jfixby.rmi.server.files.RMIFileSystemServer;
import com.jfixby.rmi.server.files.RMIFileSystemServerConfig;

public class RMIFileSystemServerTest {

	public static void main(String[] args) throws IOException {
		L.installComponent(new DesktopLogger());
		JUtils.installComponent(new RedJUtils());
		Collections.installComponent(new DesktopCollections());
		Sys.installComponent(new DesktopSystem());
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
