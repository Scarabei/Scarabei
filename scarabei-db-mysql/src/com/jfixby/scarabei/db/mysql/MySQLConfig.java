
package com.jfixby.scarabei.db.mysql;

import com.jfixby.scarabei.api.db.ConnectionParametersProvider;

class MySQLConfig {

	public String dbLogin;
	public String dbPassword;
	public String serverName;
	public String dbName;
	public boolean useSSL;
	public int port = 3306;
	public ConnectionParametersProvider connectionParamatesProvider;
	public int maxReconnects = 2;

}
