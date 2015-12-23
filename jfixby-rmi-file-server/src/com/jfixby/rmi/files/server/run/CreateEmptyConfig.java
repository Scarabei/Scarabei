package com.jfixby.rmi.files.server.run;

import java.io.IOException;

import com.jfixby.cmns.api.file.File;
import com.jfixby.cmns.api.file.LocalFileSystem;
import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.rmi.server.files.RMIFileSystemFactory;
import com.jfixby.rmi.server.files.RMIFileSystemServer;
import com.jfixby.rmi.server.files.RMIFileSystemServerConfig;

public class CreateEmptyConfig {

	public static void main(String[] args) throws IOException {
		DesktopAssembler.setup();

		byte[] data = LocalFileSystem.ApplicationHome().child("rmi-file-server.cfg").readBytes();
		ServerConfig config = Json.deserializeFromString(ServerConfig.class, JUtils.newString(data));

		RMIFileSystemServerConfig server_config = new RMIFileSystemServerConfig();
		server_config.setPort(config.port);
		server_config.setMailBoxName(config.box_name);
		File server_root = LocalFileSystem.newFile(config.server_root_path_string);
		server_config.setServerRootFolder(server_root);
		RMIFileSystemServer server = RMIFileSystemFactory.newServerInstance(server_config);
		server.start();

	}

}
