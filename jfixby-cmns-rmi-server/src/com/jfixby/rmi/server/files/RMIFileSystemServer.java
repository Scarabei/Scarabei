package com.jfixby.rmi.server.files;

import java.rmi.RemoteException;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.file.File;

public class RMIFileSystemServer {

	private int port;
	private String box_name;
	private File rootFolder;
	private String host_name;

	public RMIFileSystemServer(RMIFileSystemServerConfig config) {
		box_name = Debug.checkNull("getMailBoxName", config.getMailBoxName());
		host_name = Debug.checkNull("System.getProperty(\"java.rmi.server.hostname\")", System.getProperty("java.rmi.server.hostname"));
		port = Debug.checkNull("getPort", config.getPort());
		rootFolder = Debug.checkNull("getServerRootFolder", config.getServerRootFolder());
	}

	ServerCore core;

	public void start() {
		try {
			core = new ServerCore(this.host_name, this.box_name, this.port, this.rootFolder);
			core.start();
		} catch (RemoteException e) {
			Err.reportError(e);
		}
	}

	public void stop(boolean force) {
		core.stop(force);
	}

}
