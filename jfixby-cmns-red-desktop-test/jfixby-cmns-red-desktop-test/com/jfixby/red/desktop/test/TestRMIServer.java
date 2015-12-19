package com.jfixby.red.desktop.test;

import java.io.IOException;

import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.cmns.red.rmi.RMIServer;

public class TestRMIServer {
	public static void main(String[] args) throws IOException {
		DesktopAssembler.setup();

		RMIServer server = new RMIServer("test", 16000);
		
	}
}
