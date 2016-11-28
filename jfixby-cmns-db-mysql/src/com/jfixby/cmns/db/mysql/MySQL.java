
package com.jfixby.cmns.db.mysql;

import java.sql.SQLException;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.db.api.DBComponent;

public class MySQL implements DBComponent {
	String serverName;
	String login;
	String password;
	private final MySQLConnection connection;
	private final String dbName;
	private final boolean useSSL;
	private final int connectionDrainTime;

	public MySQL (final MySQLConfig config) {
		this.serverName = config.getServerName();
		this.login = config.getLogin();
		this.password = config.getPassword();
		this.dbName = config.getDBName();
		this.useSSL = config.useSSL();
		this.connectionDrainTime = config.getConnectionDrainTime();
		this.connection = new MySQLConnectionNormall(this.serverName, this.login, this.password, this.dbName, this.useSSL,
			this.connectionDrainTime);

	}

	public String getDBName () {
		return this.dbName;
	}

	public MySQLConnection connection () {
		if (this.connection == null) {
			Err.reportError("not connected");
		}
		return this.connection;
	}

	public void connect () throws SQLException {
		this.connection.open();
	}

	public void disconnect () {
		this.connection.close();
	}

	public MySQLTable getTable (final String name) {
		return new MySQLTable(this, name);
	}

}
