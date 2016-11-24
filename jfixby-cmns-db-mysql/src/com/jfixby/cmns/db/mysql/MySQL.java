
package com.jfixby.cmns.db.mysql;

import java.sql.SQLException;

import com.jfixby.cmns.db.api.DBComponent;

public class MySQL implements DBComponent {
	String url;
	String login;
	String password;
	private final MySQLConnection connection;
	private final String dbName;

	public MySQL (final MySQLConfig config) {
		this.url = config.getDBUrlString();
		this.login = config.getLogin();
		this.password = config.getPassword();
		this.connection = new MySQLConnection(this.url, this.login, this.password);
		this.dbName = this.url.substring(this.url.lastIndexOf("/") + 1, this.url.length());

	}

	public String getDBName () {
		return this.dbName;
	}

	public MySQLConnection connection () {
		return this.connection;
	}

	public void connect () throws SQLException {
		this.connection.connect();
	}

	public void disconnect () {
		this.connection.disconnect();
	}

	public MySQLTable getTable (final String name) {
		return new MySQLTable(this, name);
	}

}
