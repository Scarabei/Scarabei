package com.jfixby.rmi.test;

import java.io.IOException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.io.IO;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.red.debug.RedDebug;
import com.jfixby.red.desktop.collections.DesktopCollections;
import com.jfixby.red.desktop.log.DesktopLogger;
import com.jfixby.red.desktop.sys.DesktopSystem;
import com.jfixby.red.err.RedError;
import com.jfixby.red.io.RedIO;
import com.jfixby.red.util.RedJUtils;
import com.jfixby.rmi.client.files.RMIFileSystem;
import com.jfixby.rmi.client.files.RMIFileSystemConfig;

public class RMIFileSystemClientTest {
	public static void main(String[] args) throws IOException {
		L.installComponent(new DesktopLogger());
		JUtils.installComponent(new RedJUtils());
		Collections.installComponent(new DesktopCollections());
		Sys.installComponent(new DesktopSystem());
		IO.installComponent(new RedIO());
		Err.installComponent(new RedError());
		Debug.installComponent(new RedDebug());

		RMIFileSystemConfig config = new RMIFileSystemConfig();
		config.setRemoteHost("127.0.0.1");
		config.setRemotePort(16000);
		config.setRemoteBox("remote-filesystem");

		RMIFileSystem remote_file_system = new RMIFileSystem(config);
		remote_file_system.ping();

		remote_file_system.ROOT().listChildren().print("scan root");
		File A = remote_file_system.ROOT().child("a");
		File B = remote_file_system.ROOT().child("b");
		A.listChildren().print();

		remote_file_system.copyFolderContentsToFolder(A, B);

	}
}
