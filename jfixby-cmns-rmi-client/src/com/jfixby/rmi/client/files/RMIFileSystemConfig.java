package com.jfixby.rmi.client.files;

public class RMIFileSystemConfig {

	private String remote_host;
	private int port;
	private String mailbox;

	public void setRemoteBox(String mailbox) {
		this.mailbox = mailbox;
	}

	public void setRemoteHost(String remote_host) {
		this.remote_host = remote_host;
	}

	public void setRemotePort(int port) {
		this.port = port;
	}

	public String getMailBox() {
		return mailbox;
	}

	public int getRemotePort() {
		return port;
	}

	public String getRemoteHost() {
		return remote_host;
	}

}
