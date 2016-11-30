
package com.jfixby.cmns.db.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.log.L;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQLConnection {

	private SQLException e;
	private final MysqlDataSource dataSource;

	public MySQLConnection (final MysqlDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void open () {
		try {
			this.mysql_connection = this.dataSource.getConnection();
			L.d("open connection", this.mysql_connection);
		} catch (final SQLException e) {
			this.e = e;
			this.mysql_connection = null;
		}

	}

	public void close () {

		if (this.mysql_connection != null) {
			try {
				if (!this.mysql_connection.isClosed()) {
					L.d("close connection", this.mysql_connection);
					this.mysql_connection.close();
				}
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
		this.mysql_connection = null;
	}

	private Connection mysql_connection;

	public Connection getConnection () throws SQLException {
		if (this.mysql_connection == null) {
			throw this.e;
		}
		Debug.checkNull("mysql_connection", this.mysql_connection);
		return this.mysql_connection;
	}

	@Override
	public String toString () {
		return "MySQLConnection[" + this.dataSource + "]";
	}

}
