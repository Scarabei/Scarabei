
package com.jfixby.scarabei.db.mysql;

import com.jfixby.scarabei.api.db.ConnectionParametersProvider;
import com.jfixby.scarabei.api.names.ID;

class MySQLConfig {

	public String dbLogin;
	public String dbPassword;
	public String serverName;
	public ID dbName;
	public boolean useSSL;
	public int port = 3306;
	public ConnectionParametersProvider connectionParamatesProvider;
	public int maxReconnects = 2;

}
