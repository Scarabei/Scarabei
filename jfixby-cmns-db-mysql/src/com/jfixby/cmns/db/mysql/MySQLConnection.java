
package com.jfixby.cmns.db.mysql;

import java.sql.Connection;
import java.sql.SQLException;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;
import com.jfixby.cmns.api.util.StateSwitcher;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public abstract class MySQLConnection {
	final String serverName;
	final String login;
	final String password;
	final String dbName;

	final boolean useSSL;
	public final int connectionDrainTime;
	MySQLConnectionDrainer connectionDrainer = null;

	final StateSwitcher<CONNECTON_STATE> state;

	public MySQLConnection (final String serverName, final String login, final String password, final String dbName,
		final boolean useSSL, final int connectionDrainTime) {
		this.serverName = Debug.checkNull("serverName", serverName);
		this.login = Debug.checkNull("login", login);
		this.password = Debug.checkNull("password", password);
		this.dbName = Debug.checkNull("dbName", dbName);
		this.useSSL = useSSL;
		this.connectionDrainTime = connectionDrainTime;
		this.state = JUtils.newStateSwitcher(CONNECTON_STATE.CLOSED);
	}

	public boolean open () throws SQLException {
		this.state.expectState(CONNECTON_STATE.CLOSED);
		this.state.switchState(CONNECTON_STATE.OPEN);

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

	public void close () {
		this.state.expectState(CONNECTON_STATE.OPEN);
		this.state.switchState(CONNECTON_STATE.CLOSED);
		if (this.mysql_connection != null) {
			try {
				if (!this.mysql_connection.isClosed()) {
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
		return this.mysql_connection;
	}

	@Override
	public String toString () {
		return "MySQLConnection [serverName=" + this.serverName + ", login=" + this.login + "]";
	}

}
