
package com.jfixby.scarabei.red.db.stupid;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.db.Entry;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.db.TableSchema;

public class StupidEntry implements Entry {

	private final StupidTable table;

	StupidEntry (final StupidTable table) {
		this.table = table;
	}

	final Map<String, Object> values = Collections.newMap();

	@Override
	public String getValue (final String parameterName) {
		return StupidTable.toString(this.values.get(parameterName));
	}

	@Override
	public void set (final TableSchema schema, final int keyIndex, final Object value) {
		final String key = schema.getColumns().getElementAt(keyIndex);
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
