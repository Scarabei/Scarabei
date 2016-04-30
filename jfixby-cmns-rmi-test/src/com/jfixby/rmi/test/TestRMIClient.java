package com.jfixby.rmi.test;

import java.io.IOException;
import java.rmi.NotBoundException;

import com.jfixby.red.desktop.DesktopSetup;
import com.jfixby.rmi.api.RMIFace;
import com.jfixby.rmi.client.RMIClient;

public class TestRMIClient {
	public static void main(String[] args) throws IOException, NotBoundException {
		DesktopSetup.deploy();

		RMIClient client = new RMIClient("localhost", 16000, "test");
		RMIFace face = client.lookup();
		face.ping();

	}
}
