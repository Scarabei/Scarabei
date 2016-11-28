
package com.jfixby.cmns.db.mysql;

public class MySQLConnectionNormall extends MySQLConnection {

	public MySQLConnectionNormall (final String serverName, final String login, final String password, final String dbName,
		final boolean useSSL, final int connectionDrainTime) {
		super(serverName, login, password, dbName, useSSL, connectionDrainTime);
	}

}
