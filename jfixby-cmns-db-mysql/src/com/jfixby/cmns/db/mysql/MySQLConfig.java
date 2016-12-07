
package com.jfixby.cmns.db.mysql;

import com.jfixby.cmns.db.api.ConnectionParametersProvider;
import com.jfixby.cmns.db.api.DBConfig;

class MySQLConfig implements DBConfig {

	private String dbLogin;
	private String dbPassword;
	private String server;
	private String dbName;
	private boolean useSSL;
	private int port = 3306;
	private ConnectionParametersProvider connectionParamatesProvider;
	private int maxReconnects = 2;

	@Override
	public void setServerName (final String server) {
		this.server = server;
	}

	@Override
	public void setLogin (final String dbLogin) {
		this.dbLogin = dbLogin;
	}

	@Override
	public void setPassword (final String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getServerName () {
		return this.server;
	}

	public String getLogin () {
		return this.dbLogin;
	}

	public String getPassword () {
		return this.dbPassword;
	}

	public String getDBName () {
		return this.dbName;
	}

	@Override
	public void setDBName (final String dbName) {
		this.dbName = dbName;
	}

	public boolean useSSL () {
		return this.useSSL;
	}

	@Override
	public void setUseSSL (final boolean b) {
		this.useSSL = b;
	}

	@Override
	public void setPort (final int port) {
		this.port = port;
	}

	public int getPort () {
		return this.port;
	}

	@Override
	public void setConnectionParametersProvider (final ConnectionParametersProvider connectionParamatesProvider) {
		this.connectionParamatesProvider = connectionParamatesProvider;
	}

	public ConnectionParametersProvider getConnectionParametersProvider () {
		return this.connectionParamatesProvider;
	}

	public int getMaxReconnects () {
		return this.maxReconnects;
	}

	@Override
	public void setMaxReconnects (final int maxReconnects) {
		this.maxReconnects = maxReconnects;
	}

}
