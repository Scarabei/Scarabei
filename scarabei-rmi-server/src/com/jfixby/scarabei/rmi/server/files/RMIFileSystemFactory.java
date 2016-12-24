package com.jfixby.scarabei.rmi.server.files;

import java.rmi.RemoteException;

public class RMIFileSystemFactory {

	RMIFileSystemFactory() {
	}

	public static RMIFileSystemServer newServerInstance(RMIFileSystemServerConfig config) throws RemoteException {
		return new RMIFileSystemServer(config);
	}

}
