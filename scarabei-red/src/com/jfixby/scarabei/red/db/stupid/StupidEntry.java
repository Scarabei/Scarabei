
package com.jfixby.scarabei.red.db.stupid;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.db.Entry;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.log.L;

public class StupidEntry implements Entry {

	private final StupidTable table;

	StupidEntry (final StupidTable table) {
		this.table = table;
	}

	final Map<String, Object> values = Collections.newMap();

	@Override
	public Object getValue (final String parameterName) {
		return this.values.get(parameterName);
	}

	@Override
	public void setValue (final String key, final Object value) {
		final boolean noKey = this.getOwner().getSchema().indexOf(key) >= 0;
		L.e(this.getOwner().getSchema());
		Debug.checkTrue("Key<" + key + "> exists", noKey);
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
