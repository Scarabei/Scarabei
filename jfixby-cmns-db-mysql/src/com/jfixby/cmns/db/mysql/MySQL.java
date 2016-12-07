
package com.jfixby.cmns.db.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.db.api.ConnectionParametersProvider;
import com.jfixby.cmns.db.api.DBConfig;
import com.jfixby.cmns.db.api.DataBase;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

class MySQL implements DataBase {
	String serverName;
	String login;
	String password;
	private final String dbName;
	private final boolean useSSL;
	private MysqlDataSource dataSource;
	final Map<String, MySQLTable> tables = Collections.newMap();
	private int port;
	private final ConnectionParametersProvider connectionParamatesProvider;
	private final int maxReconnects;

	MySQL (final DBConfig config) {
		this.dbName = Debug.checkNull("dbName", config.getDBName());
		this.useSSL = config.useSSL();
		this.connectionParamatesProvider = config.getConnectionParametersProvider();
		this.maxReconnects = config.getMaxReconnects();

		if (this.connectionParamatesProvider == null) {
			this.serverName = Debug.checkNull("serverName", config.getServerName());
			this.login = Debug.checkNull("login", config.getLogin());
			this.password = Debug.checkNull("password", config.getPassword());
			this.port = Debug.checkNull("port", config.getPort());

			L.d("connecting", this.serverName);
			this.dataSource = new MysqlDataSource();
			this.dataSource.setUser(this.login);
			this.dataSource.setPassword(this.password);
			this.dataSource.setPort(this.port);
			this.dataSource.setServerName(this.serverName);
			this.dataSource.setUseSSL(this.useSSL);
			this.dataSource.setDatabaseName(this.dbName);
			this.dataSource.setAutoReconnect(true);
			try {
				this.dataSource.setConnectTimeout(1000);
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public String getDBName () {
		return this.dbName;
	}

	@Override
	public MySQLTable getTable (final String name) throws IOException {
		Debug.checkNull("name", name);
		Debug.checkEmpty("name", name);
		MySQLTable table = this.tables.get(name);
		if (table == null) {
			table = new MySQLTable(this, name);
			this.tables.put(name, table);
		}
		return table;
	}

	public MySQLConnection obtainConnection () {
		final MySQLConnection connection = new MySQLConnection(this);
		connection.open();
		return connection;
	}

	public void releaseConnection (final MySQLConnection connection) {
		connection.close();
	}

	synchronized Connection open () throws SQLException {
		if (this.dataSource != null) {
			return this.dataSource.getConnection();
		}
		this.serverName = this.connectionParamatesProvider.getHost();
		this.login = this.connectionParamatesProvider.getLogin();
		this.password = this.connectionParamatesProvider.getPassword();
		this.port = this.connectionParamatesProvider.getPort();
		if (this.serverName == null) {
			throw new SQLException("Missing connection configuration: serverName");
		}
		if (this.login == null) {
			throw new SQLException("Missing connection configuration: login");
		}
		if (this.password == null) {
			throw new SQLException("Missing connection configuration: password");
		}
		if (this.port == -1) {
			throw new SQLException("Missing connection configuration: port");
		}

		L.d("connecting", this.serverName);
		this.dataSource = new MysqlDataSource();
		this.dataSource.setUser(this.login);
		this.dataSource.setPassword(this.password);
		this.dataSource.setPort(this.port);
		this.dataSource.setServerName(this.serverName);
		this.dataSource.setUseSSL(this.useSSL);
		this.dataSource.setDatabaseName(this.dbName);
		this.dataSource.setAutoReconnect(true);
		this.dataSource.setMaxReconnects(this.maxReconnects);
		try {
			this.dataSource.setConnectTimeout(2000);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return this.dataSource.getConnection();
	}

	public String getUrl () {
		if (this.dataSource == null) {
			return null;
		}
		return this.dataSource.getURL();
	}

	synchronized boolean close (final Connection mysql_connection) {
		if (mysql_connection != null) {
			try {
				if (!mysql_connection.isClosed()) {
					mysql_connection.close();
					return true;
				}
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
