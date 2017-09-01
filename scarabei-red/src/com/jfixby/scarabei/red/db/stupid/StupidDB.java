
package com.jfixby.scarabei.red.db.stupid;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.db.DataBase;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.names.ID;

public class StupidDB implements DataBase {

	private final ID dbName;
	final Map<ID, StupidTable> tables = Collections.newMap();

	public StupidDB (final StupidDBConfig config) {
		this.dbName = config.dbName;
		Debug.checkNull("dbName", this.dbName);

	}

	@Override
	public Table getTable (final ID tableName) throws IOException {
		Debug.checkNull("tableName", tableName);
		StupidTable table = this.tables.get(tableName);
		if (table == null) {
			table = new StupidTable(this, tableName);
			this.tables.put(tableName, table);
		}
		return table;
	}

	@Override
	public ID getDBName () {
		return this.dbName;
	}

}
