package com.jfixby.rmi.server;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.jfixby.cmns.api.log.L;
import com.jfixby.rmi.api.RMIFace;

public class RMIServer extends UnicastRemoteObject implements RMIFace {
	private static final long serialVersionUID = -393912772332642695L;
	private Registry stReg;

	public RMIServer(String postOfficeID, int port) throws RemoteException {
		super();

		stReg = LocateRegistry.createRegistry(port);

		if (System.getSecurityManager() == null) {

		}
		stReg.rebind(postOfficeID, this);
		System.out.println("Open:   " + stReg);
		System.out.println("			at: rmi://localhost:" + port + "/" + postOfficeID);
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
		L.d("server: ping received");
		return true;
	}

}
