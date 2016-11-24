
package com.jfixby.cmns.db.mysql;

import com.jfixby.cmns.db.api.DBComponent;

public class MySQL implements DBComponent {

	public MySQL (final MySQLConfig config) {
	}

	@Override
	public MySQLTestConnectionResult testConnection () {
		return new MySQLTestConnectionResult();
	}

}
