
package com.jfixby.cmns.db.mysql;

import java.sql.SQLException;

import com.jfixby.cmns.db.api.DBComponent;

public class MySQL implements DBComponent {
	String serverName;
	String login;
	String password;
	private final MySQLConnection connection;
	private final String dbName;
	private final boolean useSSL;

	public MySQL (final MySQLConfig config) {
		this.serverName = config.getServerName();
		this.login = config.getLogin();
		this.password = config.getPassword();
		this.dbName = config.getDBName();
		this.useSSL = config.useSSL();
		this.connection = new MySQLConnection(this.serverName, this.login, this.password, this.dbName, this.useSSL);

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
