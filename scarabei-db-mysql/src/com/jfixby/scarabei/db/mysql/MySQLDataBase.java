
package com.jfixby.scarabei.db.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.db.ConnectionParametersProvider;
import com.jfixby.scarabei.api.db.DataBase;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.IntegerMath;
import com.jfixby.scarabei.api.names.ID;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

class MySQLDataBase implements DataBase {
	String serverName;
	String login;
	String password;
	ID dbName;
	private final boolean useSSL;
	private MysqlDataSource dataSource;
	final Map<String, MySQLTable> tables = Collections.newMap();
	private int port;
	private final ConnectionParametersProvider connectionParamatesProvider;
	private final int maxReconnects;

	MySQLDataBase (final MySQLConfig config) {
		this.useSSL = config.useSSL;
		this.connectionParamatesProvider = config.connectionParamatesProvider;
		this.maxReconnects = (int)IntegerMath.limit(1, config.maxReconnects, Integer.MAX_VALUE);

		if (this.connectionParamatesProvider == null) {
			this.serverName = Debug.checkNull("serverName", config.serverName);
			this.login = Debug.checkNull("dbLogin", config.dbLogin);
			this.password = Debug.checkNull("dbPassword", config.dbPassword);
			this.port = Debug.checkNull("port", config.port);
			this.dbName = Debug.checkNull("dbName", config.dbName);

			L.d("connecting", this.serverName);
			this.dataSource = new MysqlDataSource();
			this.dataSource.setUser(this.login);
			this.dataSource.setPassword(this.password);
			this.dataSource.setPort(this.port);
			this.dataSource.setServerName(this.serverName);
			this.dataSource.setUseSSL(this.useSSL);
			this.dataSource.setDatabaseName(this.idToDBName(this.dbName));
			this.dataSource.setAutoReconnect(true);
			try {
				this.dataSource.setConnectTimeout(1000);
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static String idToDBName (final ID dbName) {
		return dbName.toString("_");
	}

	@Override
	public ID getDBName () {
		return this.dbName;
	}

	@Override
	public MySQLTable getTable (final String name) throws IOException {
		Debug.checkNull("name", name);
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
		this.dbName = this.connectionParamatesProvider.getDBName();
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
		this.dataSource.setDatabaseName(this.idToDBName(this.dbName));
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
