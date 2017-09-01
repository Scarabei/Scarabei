
package com.jfixby.scarabei.red.db.simple;

import java.io.IOException;
import java.util.LinkedHashMap;

import com.jfixby.scarabei.api.db.Entry;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.Utils;

public class SimpleEntry implements Entry {

	private final SimpleTable table;

	SimpleEntry (final SimpleTable table) {
		this.table = table;
	}

	@Override
	public String toString () {
		return "" + this.values + "";
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.values == null) ? 0 : this.values.hashCode());
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final SimpleEntry other = (SimpleEntry)obj;
		if (this.values == null) {
			if (other.values != null) {
				return false;
			}
		} else if (!this.sameMaps(this.values, other.values)) {
			return false;
		}
		return true;
	}

	private boolean sameMaps (final LinkedHashMap<String, Object> x, final LinkedHashMap<String, Object> y) {
		if (x == y) {
			return true;
		}
		for (final String k : x.keySet()) {
			final Object Vx = x.get(k);
			final Object Vy = y.get(k);
			if (!Utils.equalObjects(Vx, Vy)) {
				return false;
			}
		}
		return true;
	}

	final LinkedHashMap<String, Object> values = new LinkedHashMap<String, Object>();

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
		this.values.put(key, value);
	}

	void set (final String key, final Object value) throws IOException {
		final boolean hasKey = this.getOwner().getSchema().indexOf(key) >= 0;
		if (!hasKey) {
			L.e("schema columns", this.getOwner().getSchema().getColumns());
			throw new IOException("Key<" + key + "> exists");
		}

		this.values.put(key, value);
	}

	@Override
	public Table getOwner () {
		return this.table;
	}

}
