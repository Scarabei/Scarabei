package com.jfixby.red.desktop.test;

import java.io.IOException;
import java.rmi.NotBoundException;

import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.cmns.red.rmi.RMIClient;
import com.jfixby.cmns.red.rmi.RMIFace;

public class TestRMIClient {
	public static void main(String[] args) throws IOException, NotBoundException {
		DesktopAssembler.setup();

		RMIClient client = new RMIClient("localhost", 16000, "test");
		RMIFace face = client.lookup();
		face.ping();

	}
}
