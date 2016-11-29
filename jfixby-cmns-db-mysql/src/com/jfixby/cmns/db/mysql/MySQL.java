
package com.jfixby.cmns.db.mysql;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.db.api.DBComponent;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class MySQL implements DBComponent {
	String serverName;
	String login;
	String password;
	private final String dbName;
	private final boolean useSSL;
	private final MysqlDataSource dataSource;

	public MySQL (final MySQLConfig config) {
		this.serverName = Debug.checkNull("serverName", config.getServerName());
		this.login = Debug.checkNull("login", config.getLogin());
		this.password = Debug.checkNull("password", config.getPassword());
		this.dbName = Debug.checkNull("dbName", config.getDBName());

		this.useSSL = config.useSSL();

		L.d("connecting", this.serverName);
		this.dataSource = new MysqlDataSource();
		this.dataSource.setUser(this.login);
		this.dataSource.setPassword(this.password);
		this.dataSource.setServerName(this.serverName);
		this.dataSource.setUseSSL(this.useSSL);
		this.dataSource.setDatabaseName(this.dbName);
		this.dataSource.setAutoReconnect(true);

	}

	public String getDBName () {
		return this.dbName;
	}

	public MySQLTable getTable (final String name) {
		return new MySQLTable(this, name);
	}

	public MySQLConnection obtainConnection () {
		final MySQLConnection connection = new MySQLConnection(this.dataSource);
		connection.open();
		return connection;
	}

	public void releaseConnection (final MySQLConnection connection) {
		connection.close();
	}

}
