
package com.jfixby.scarabei.db.mysql;

import com.jfixby.scarabei.db.api.DBComponent;
import com.jfixby.scarabei.db.api.DBConfig;
import com.jfixby.scarabei.db.api.DataBase;

public class MySQLDB implements DBComponent {

	@Override
	public DBConfig newDBConfig () {
		return new MySQLConfig();
	}

	@Override
	public DataBase newDB (final DBConfig config) {
		return new MySQL(config);
	}

}
