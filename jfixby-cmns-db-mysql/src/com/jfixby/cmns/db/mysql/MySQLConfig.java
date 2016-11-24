
package com.jfixby.cmns.db.mysql;

public class MySQLConfig {

	private String dbLogin;
	private String dbPassword;
	private String dbUrlString;

	public void setDBUrlString (final String dbUrlString) {
		this.dbUrlString = dbUrlString;
	}

	public void setLogin (final String dbLogin) {
		this.dbLogin = dbLogin;
	}

	public void setPassword (final String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDBUrlString () {
		return this.dbUrlString;
	}

	public String getLogin () {
		return this.dbLogin;
	}

	public String getPassword () {
		return this.dbPassword;
	}

}
