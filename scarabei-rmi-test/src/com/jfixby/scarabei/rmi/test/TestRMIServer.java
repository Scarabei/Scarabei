package com.jfixby.scarabei.rmi.test;

import java.io.IOException;

import com.jfixby.scarabei.api.desktop.DesktopSetup;
import com.jfixby.scarabei.rmi.server.RMIServer;

public class TestRMIServer {
	public static void main(String[] args) throws IOException {
		DesktopSetup.deploy();

		RMIServer server = new RMIServer("test", 16000);
		
	}
}
