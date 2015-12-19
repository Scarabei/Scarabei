package com.jfixby.cmns.red.rmi;

import java.rmi.Remote;

public interface RMIFace extends Remote {

	public boolean ping() throws java.rmi.RemoteException;

}
