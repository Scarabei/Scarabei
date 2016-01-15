package com.jfixby.rmi.test;

import java.io.IOException;

import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.rmi.server.RMIServer;

public class TestRMIServer {
	public static void main(String[] args) throws IOException {
		DesktopAssembler.setup();

		RMIServer server = new RMIServer("test", 16000);
		
	}
}
