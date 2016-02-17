package com.jfixby.rmi.files.server.run;

import java.io.IOException;

import com.jfixby.cmns.adopted.gdx.json.GdxJson;
import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.red.desktop.DesktopAssembler;
import com.jfixby.red.desktop.filesystem.unix.UnixFileSystem;
import com.jfixby.rmi.server.files.RMIFileSystemFactory;
import com.jfixby.rmi.server.files.RMIFileSystemServer;
import com.jfixby.rmi.server.files.RMIFileSystemServerConfig;

public class RunFileServer {

	public static void main(String[] args) throws IOException {
		DesktopAssembler.setup();

		LocalFileSystem.deInstallCurrentComponent();
		LocalFileSystem.installComponent(new UnixFileSystem());

		Json.installComponent(new GdxJson());

		File config_file = LocalFileSystem.ApplicationHome().child("rmi-file-server.cfg");
		if (!config_file.exists()) {
			createnewConfigFile(config_file);
		}

		byte[] data = config_file.readBytes();
		String string = JUtils.newString(data);
		ServerConfig config = Json.deserializeFromString(ServerConfig.class, string);
		L.d("", string);

		RMIFileSystemServerConfig server_config = new RMIFileSystemServerConfig();
		server_config.setPort(config.port);
		server_config.setMailBoxName(config.box_name);
		File server_root = LocalFileSystem.newFile(config.server_root_path_string);
		L.d("Starting RMI file server at " + server_root);
		server_root.listChildren().print();

		server_config.setServerRootFolder(server_root);

		RMIFileSystemServer server = RMIFileSystemFactory.newServerInstance(server_config);
		server.start();

	}

	private static void createnewConfigFile(File config_file) throws IOException {
		ServerConfig cfg = new ServerConfig();
		cfg.port = 16000;
		cfg.box_name = "remote-filesystem";
		cfg.server_root_path_string = "";
		String string = Json.serializeToString(cfg);
		config_file.writeBytes(string.getBytes());
	}
}
