
package com.jfixby.scarabei.db.api;

public interface DBConfig {

	void setConnectionParametersProvider (ConnectionParametersProvider connectionParamatesProvider);

	void setDBName (String dbName);

	void setUseSSL (boolean b);

	void setMaxReconnects (int i);

	void setServerName (String dbServer);

	void setLogin (String dbLogin);

	void setPassword (String dbPassword);

	void setPort (int i);

	String getDBName ();

	boolean useSSL ();

	ConnectionParametersProvider getConnectionParametersProvider ();

	int getMaxReconnects ();

	String getServerName ();

	String getLogin ();

	String getPassword ();

	int getPort ();

}
