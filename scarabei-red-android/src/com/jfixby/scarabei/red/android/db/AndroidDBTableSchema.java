
package com.jfixby.scarabei.red.android.db;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.db.TableSchema;
import com.jfixby.scarabei.api.log.L;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

class AndroidDBTableSchema implements TableSchema {

	private final AndroidDBTable mySQLTable;
	private final Set<String> columns = Collections.newSet();

	public AndroidDBTableSchema (final AndroidDBTable mySQLTable) throws IOException {
		this.mySQLTable = mySQLTable;
		final AndroidDBHelper helper = this.mySQLTable.db.obtainConnection();
		try {

			final SQLiteDatabase db = helper.getReadableDatabase();
			final String tableName = mySQLTable.sql_table_name;
			final Cursor c1 = db.rawQuery("SELECT * FROM " + tableName, null);
			c1.moveToFirst();
			final String[] columnNames = c1.getColumnNames();
			for (int j = 0; j < columnNames.length; j++) {
				c1.move(j);
				this.columns.add(columnNames[j]);
			}

		} finally {
			this.mySQLTable.db.releaseConnection(helper);
		}
	}

	@Override
	public Collection<String> getColumns () {
		return this.columns;
	}

	@Override
	public int indexOf (final String key) {
		final int result = this.columns.indexOf(key);
		if (result == -1) {
			L.e("schema", this.columns);
		}
		return result;
	}

}
