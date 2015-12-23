package com.jfixby.rmi.server.files;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.jfixby.cmns.api.file.File;

public class RMIFileSystemServer extends UnicastRemoteObject {
	private static final long serialVersionUID = -7042525299205791827L;
	private int port;
	private String box_name;
	private File rootFolder;

	public RMIFileSystemServer(RMIFileSystemServerConfig config) throws RemoteException {
		box_name = config.getMailBoxName();
		port = config.getPort();
		rootFolder = config.getServerRootFolder();
	}

}
