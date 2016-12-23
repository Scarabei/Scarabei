package com.jfixby.rmi.server.files;

import com.jfixby.cmns.api.file.File;

public class RMIFileSystemServerConfig {

	private int port;
	private File server_root;
	private String mailbox;
	
	public String getMailBoxName() {
		return mailbox;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setMailBoxName(String mailbox) {
		this.mailbox = mailbox;
	}

	public void setServerRootFolder(File server_root) {
		this.server_root = server_root;
	}

	public File getServerRootFolder() {
		return this.server_root;
	}



}
