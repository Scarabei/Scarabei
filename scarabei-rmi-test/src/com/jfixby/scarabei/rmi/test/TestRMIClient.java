package com.jfixby.scarabei.rmi.test;

import java.io.IOException;
import java.rmi.NotBoundException;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.rmi.api.RMIFace;
import com.jfixby.scarabei.rmi.client.RMIClient;

public class TestRMIClient {
	public static void main(String[] args) throws IOException, NotBoundException {
		ScarabeiDesktop.deploy();

		RMIClient client = new RMIClient("localhost", 16000, "test");
		RMIFace face = client.lookup();
		face.ping();

	}
}
