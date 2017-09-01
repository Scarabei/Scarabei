
package com.jfixby.scarabei.db.realm;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.db.TableSchema;
import com.jfixby.scarabei.api.log.L;

import io.realm.Realm;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

class RealmTableSchema implements TableSchema {

	private final RealmTable mySQLTable;
	private final Set<String> columns = Collections.newSet();
	private final RealmObjectSchema realmSchema;

	public RealmTableSchema (final RealmTable mySQLTable) {
		this.mySQLTable = mySQLTable;
		final Realm connection = this.mySQLTable.db.obtainConnection();
		final RealmSchema dbSchema = connection.getSchema();
		this.realmSchema = dbSchema.get(this.mySQLTable.db.toDBName(mySQLTable.sql_table_name));
		final java.util.Set<String> names = this.realmSchema.getFieldNames();
		this.columns.addAll(names);
		L.d(mySQLTable.sql_table_name + " : columns", this.columns);
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
