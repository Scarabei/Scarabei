
package com.jfixby.cmns.db.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.log.L;

public class MySQLConnection {

	private SQLException e;
	private final MySQL mySQL;

	public MySQLConnection (final MySQL mySQL) {
		this.mySQL = mySQL;
	}

	public boolean open () {
		try {
			L.d(" open connection", this);
			this.mysql_connection = this.mySQL.open();
			return true;
		} catch (final SQLException e) {
			this.e = e;
			this.mysql_connection = null;
			return false;
		}

	}

	public void close () {
		if (this.mySQL.close(this.mysql_connection)) {
			L.d("close connection", this);
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
		return "MySQLConnection[" + this.mySQL.getUrl() + "]";
	}

	@Override
	protected void finalize () throws Throwable {
		super.finalize();
		if (this.mysql_connection != null) {
			Err.reportGCLeak("MySQLConnection is not released", this);
		}
	}

	public boolean checkIsOpen () throws IOException {
		if (this.mysql_connection != null) {
			return true;
		}
		if (this.e != null) {
			throw new IOException(this.e);
		}
		throw new IOException("Failed to connect " + this);
	}

}
