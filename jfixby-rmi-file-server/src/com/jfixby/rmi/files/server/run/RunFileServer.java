
package com.jfixby.rmi.files.server.run;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.RedJson;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.red.desktop.DesktopSetup;
import com.jfixby.red.desktop.filesystem.unix.UnixFileSystem;
import com.jfixby.rmi.server.files.RMIFileSystemFactory;
import com.jfixby.rmi.server.files.RMIFileSystemServer;
import com.jfixby.rmi.server.files.RMIFileSystemServerConfig;

public class RunFileServer {

	public static void main (final String[] args) throws IOException {
		DesktopSetup.deploy();

		LocalFileSystem.deInstallCurrentComponent();
		LocalFileSystem.installComponent(new UnixFileSystem());

		Json.installComponent(new RedJson());

		final File config_file = LocalFileSystem.ApplicationHome().child("rmi-file-server.cfg");
		if (!config_file.exists()) {
			createnewConfigFile(config_file);
		}

		final ByteArray data = config_file.readBytes();
		final String string = JUtils.newString(data);
		final ServerConfig config = Json.deserializeFromString(ServerConfig.class, string);
		L.d("", string);

		final RMIFileSystemServerConfig server_config = new RMIFileSystemServerConfig();
		server_config.setPort(config.port);
		server_config.setMailBoxName(config.box_name);
		final File server_root = LocalFileSystem.newFile(config.server_root_path_string);
		L.d("Starting RMI file server at " + server_root);
		server_root.listDirectChildren().print("server_root");

		server_config.setServerRootFolder(server_root);

		final RMIFileSystemServer server = RMIFileSystemFactory.newServerInstance(server_config);
		server.start();

	}

	private static void createnewConfigFile (final File config_file) throws IOException {
		final ServerConfig cfg = new ServerConfig();
		cfg.port = 16000;
		cfg.box_name = "remote-filesystem";
		cfg.server_root_path_string = "";
		final String string = Json.serializeToString(cfg).toString();
		config_file.writeBytes(JUtils.newByteArray(string.getBytes()));
	}
}
