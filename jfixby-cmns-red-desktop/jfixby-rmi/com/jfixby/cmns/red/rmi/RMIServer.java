package com.jfixby.cmns.red.rmi;

import java.rmi.NoSuchObjectException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements RMIFace {
	private static final long serialVersionUID = -393912772332642695L;
	private Registry stReg;
	private RMISecurityManager RM;

	public RMIServer(String postOfficeID, int port) throws RemoteException {
		super();

		stReg = LocateRegistry.createRegistry(port);

		if (System.getSecurityManager() == null) {
			RM = new RMISecurityManager();
			System.setSecurityManager(RM);

		}
		stReg.rebind(postOfficeID, this);
		System.out.println("Open:   " + stReg);
		System.out.println("			at: rmi://localhost:" + port + "/"
				+ postOfficeID);
		// /System.out.println(stReg.list().toString());

	}

	public void ShutDown(boolean force) {

		try {
			UnicastRemoteObject.unexportObject(this, force);
			UnicastRemoteObject.unexportObject(stReg, force);
			System.out.println("Closed: " + stReg);

		} catch (NoSuchObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean ping() throws RemoteException {
		return true;
	}

}
