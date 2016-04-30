package com.jfixby.rmi.test;

import java.io.IOException;

import com.jfixby.red.desktop.DesktopSetup;
import com.jfixby.rmi.server.RMIServer;

public class TestRMIServer {
	public static void main(String[] args) throws IOException {
		DesktopSetup.deploy();

		RMIServer server = new RMIServer("test", 16000);
		
	}
}
