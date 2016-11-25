
package com.jfixby.cmns.db.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.log.L;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLConnection {

	private final String serverName;
	private final String login;
	private final String password;
	private final String dbName;
	private Connection mysql_connection;
	private final boolean useSSL;

	public MySQLConnection (final String serverName, final String login, final String password, final String dbName,
		final boolean useSSL) {
		this.serverName = Debug.checkNull("serverName", serverName);
		this.login = Debug.checkNull("login", login);
		this.password = Debug.checkNull("password", password);
		this.dbName = Debug.checkNull("dbName", dbName);
		this.useSSL = useSSL;
	}

	@Override
	public String toString () {
		return "MySQLConnection [serverName=" + this.serverName + ", login=" + this.login + "]";
	}

	public boolean connect () throws SQLException {

		L.d("connecting", this.serverName);

		final MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(this.login);
		dataSource.setPassword(this.password);
		dataSource.setServerName(this.serverName);
		dataSource.setUseSSL(this.useSSL);
		dataSource.setDatabaseName(this.dbName);

		this.mysql_connection = dataSource.getConnection();

		L.d("connecting", "OK");
		return true;

	}

	public void disconnect () {
		try {
			this.mysql_connection.close();
		} catch (final SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection () {
		return this.mysql_connection;
	}

}
