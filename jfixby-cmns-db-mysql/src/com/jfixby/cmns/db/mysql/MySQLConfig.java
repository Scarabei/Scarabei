
package com.jfixby.cmns.db.mysql;

public class MySQLConfig {

	private String dbLogin;
	private String dbPassword;
	private String server;
	private String dbName;
	private boolean useSSL;

	public void setServerName (final String server) {
		this.server = server;
	}

	public void setLogin (final String dbLogin) {
		this.dbLogin = dbLogin;
	}

	public void setPassword (final String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getServerName () {
		return this.server;
	}

	public String getLogin () {
		return this.dbLogin;
	}

	public String getPassword () {
		return this.dbPassword;
	}

	public String getDBName () {
		return this.dbName;
	}

	public void setDBName (final String dbName) {
		this.dbName = dbName;
	}

	public boolean useSSL () {
		return this.useSSL;
	}

	public void setUseSSL (final boolean b) {
		this.useSSL = b;
	}

}
