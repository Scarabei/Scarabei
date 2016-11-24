
package com.jfixby.cmns.db.mysql;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Map;

public class MySQLEntry {

	final Map<String, String> values = Collections.newMap();

	void set (final String key, final String value) {
		this.values.put(key, value);
	}

	@Override
	public String toString () {
		return "" + this.values + "";
	}

	public void set (final MySQLTableSchema schema, final int keyIndex, final String value) {
		final String key = schema.columns.getElementAt(keyIndex);
		this.set(key, value);
	}

}
