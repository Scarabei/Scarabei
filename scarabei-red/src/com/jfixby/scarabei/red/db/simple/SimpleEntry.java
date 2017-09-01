
package com.jfixby.scarabei.red.db.simple;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.db.Entry;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.log.L;

public class SimpleEntry implements Entry {

	private final SimpleTable table;

	SimpleEntry (final SimpleTable table) {
		this.table = table;
	}

	final Map<String, Object> values = Collections.newMap();

	@Override
	public Object getValue (final String parameterName) {
		return this.values.get(parameterName);
	}

	@Override
	public void setValue (final String key, final Object value) {
		final boolean hasKey = this.getOwner().getSchema().indexOf(key) >= 0;
		if (!hasKey) {
			L.e("schema columns", this.getOwner().getSchema().getColumns());
		}

		Debug.checkTrue("Key<" + key + "> exists", hasKey);
		this.set(key, value);
	}

	void set (final String key, final Object value) {
		this.values.put(key, value);
	}

	@Override
	public Table getOwner () {
		return this.table;
	}

}
