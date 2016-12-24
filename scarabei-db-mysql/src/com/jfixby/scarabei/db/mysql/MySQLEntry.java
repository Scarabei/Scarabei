
package com.jfixby.scarabei.db.mysql;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.db.api.Entry;
import com.jfixby.scarabei.db.api.TableSchema;

class MySQLEntry implements Entry {

	public MySQLEntry () {
	}

	final Map<String, String> values = Collections.newMap();

	void set (final String key, final Object value) {
		this.values.put(key, value + "");
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
		return this.values.get(key);
	}

}
