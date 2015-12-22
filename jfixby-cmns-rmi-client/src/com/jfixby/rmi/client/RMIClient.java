package com.jfixby.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.jfixby.rmi.api.RMIFace;

public class RMIClient<T extends RMIFace> {

	private String office;
	private String host;
	private int port;
	private String add;

	public RMIClient(String ip_name, int port, String postOfficeId) {
		this.host = ip_name;
		this.office = postOfficeId;
		this.port = port;
		this.add = buildAddressString(ip_name, port, postOfficeId);
	}

	public String getPostOfficeId() {
		return this.office;
	}

	public String getHostName() {
		return this.host;
	}

	public int getPort() {
		return this.port;
	}

	public String getAddress() {
		return this.add;
	}

	public static String buildAddressString(String ip_name, int port, String postOfficeId) {
		return "rmi://" + ip_name + ":" + port + "/" + postOfficeId;
	}

	public static <T extends RMIFace> T lookup(String ip_name, int port, String pid, Class<T> clazz) throws MalformedURLException, RemoteException, NotBoundException {

		return lookup(buildAddressString(ip_name, port, pid), clazz);

	}

	@SuppressWarnings("unchecked")
	public static <T extends RMIFace> T lookup(String fullAddress, Class<T> clazz) throws MalformedURLException, RemoteException, NotBoundException {

		return (T) Naming.lookup(fullAddress);

	}

	@SuppressWarnings("unchecked")
	public T lookup() throws MalformedURLException, RemoteException, NotBoundException {
		return (T) Naming.lookup(this.getAddress());

	}

	public <E extends RMIFace> E lookup(Class<E> remoteInterfaceType) throws MalformedURLException, RemoteException, NotBoundException {
		return (E) lookup(this.getAddress(), remoteInterfaceType);

	}

}
