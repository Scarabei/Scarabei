
package com.jfixby.cmns.db.mysql;

import com.jfixby.cmns.db.api.DBComponent;
import com.jfixby.cmns.db.api.DBConfig;
import com.jfixby.cmns.db.api.DataBase;

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
