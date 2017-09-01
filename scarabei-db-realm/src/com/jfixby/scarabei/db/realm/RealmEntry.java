
package com.jfixby.scarabei.db.realm;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.db.Entry;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.db.TableSchema;
import com.jfixby.scarabei.api.err.Err;

class RealmEntry implements Entry {

	private final RealmTable table;

	public RealmEntry (final RealmTable table) {
		this.table = table;
	}

	final Map<String, Object> values = Collections.newMap();

	void set (final String key, final Object value) {
		Err.throwNotImplementedYet();
		this.values.put(key, value + "");// NULL?
	}

	@Override
	public String toString () {
		return "" + this.values + "";
	}

	@Override
	public void set (final TableSchema schema, final int keyIndex, final Object value) {
		final String key = schema.getColumns().getElementAt(keyIndex);
		this.set(key, value);
	}

	@Override
	public String getValue (final String key) {
		Err.throwNotImplementedYet();
		return this.table.toString(this.values.get(key));// NULL?
	}

	@Override
	public Table getOwner () {
		return this.table;
	}
}
