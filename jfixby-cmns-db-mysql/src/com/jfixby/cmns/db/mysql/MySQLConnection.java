
package com.jfixby.cmns.db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.log.L;

public class MySQLConnection {

	private final String url_db_string;
	private final String login;
	private final String password;

	private Connection mysql_connection;

	public MySQLConnection (final String url, final String login, final String password) {
		this.url_db_string = Debug.checkNull("url", url);
		this.login = Debug.checkNull("login", login);
		this.password = Debug.checkNull("password", password);
	}

	@Override
	public String toString () {
		return "MySQLConnection [url=" + this.url_db_string + ", login=" + this.login + "]";
	}

	public boolean connect () throws SQLException {

		L.d("connecting", this.url_db_string);
		this.mysql_connection = DriverManager.getConnection(this.url_db_string, this.login, this.password);

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
