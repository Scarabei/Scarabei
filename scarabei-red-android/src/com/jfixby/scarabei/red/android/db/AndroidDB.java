
package com.jfixby.scarabei.red.android.db;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.db.DataBase;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AndroidDB implements DataBase {

	private final AndroidDBHelper helper;
	private final String dbName;
	final Map<String, AndroidDBTable> tables = Collections.newMap();

	public AndroidDB (final AndroidDBConfig config) {
		Err.throwNotImplementedYet();
		Debug.checkNull("dbName", config.dbName);
		this.dbName = Debug.checkEmpty("dbName", config.dbName);
		this.helper = new AndroidDBHelper(this.dbName);

	}

	@Override
	public Table getTable (final String tableName) throws IOException {
		Debug.checkNull("tableName", tableName);
		Debug.checkEmpty("tableName", tableName);

		AndroidDBTable table = this.tables.get(tableName);
		if (table == null) {
			final SQLiteDatabase db = this.helper.getReadableDatabase();
			final Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
			final String[] tableNames = c.getColumnNames();
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				final int tablesCount = c.getColumnCount();
				for (int i = 0; i < tablesCount; i++) {
					final String tableName_i = c.getString(i);
					if (tableName_i.equals(tableName)) {
						table = new AndroidDBTable(this, tableName);
						this.tables.put(tableName, table);
						return table;
					}
				}
			}
			L.e("tableNames", tableNames);
			throw new IOException("Table <" + tableName + "> not found");
		}
		return table;
	}

	@Override
	public String getDBName () {
		return this.dbName;
	}

	public AndroidDBHelper obtainConnection () {
		return this.helper;
	}

	public void releaseConnection (final AndroidDBHelper helper) {
	}

}
