
package com.jfixby.scarabei.red.db.simple;

import java.io.IOException;
import java.util.ArrayList;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.db.TableSchema;

public class SimpleTableSchema implements TableSchema {

	final List<String> columns = Collections.newList();

	public SimpleTableSchema () {
	}

	@Override
	public int indexOf (final String key) {
		return this.columns.indexOf(key);
	}

	@Override
	public Collection<String> getColumns () {
		return this.columns;
	}

	public void addAll (final ArrayList<String> columns) throws IOException {
		for (final String c : columns) {
			if (c == null) {
				throw new IOException("Column name is NULL");
			}
			if (c.equals("")) {
				throw new IOException("Column name is empty");
			}
			this.columns.add(c);
		}
	}

}
