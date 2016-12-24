package com.jfixby.scarabei.rmi.api;

import java.rmi.Remote;

public interface RMIFace extends Remote {

	public boolean ping() throws java.rmi.RemoteException;

}
