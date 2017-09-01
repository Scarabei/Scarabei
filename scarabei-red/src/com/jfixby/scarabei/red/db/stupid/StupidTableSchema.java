
package com.jfixby.scarabei.red.db.stupid;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.db.TableSchema;

public class StupidTableSchema implements TableSchema {

	@Override
	public String toString () {
		return "TableSchema<" + this.stupidTable.getName() + "> columns=" + this.columns + "";
	}

	private final StupidTable stupidTable;

	final List<String> columns = Collections.newList();

	public StupidTableSchema (final StupidTable stupidTable) {
		this.stupidTable = stupidTable;
	}

	@Override
	public int indexOf (final String key) {
		return this.columns.indexOf(key);
	}

	@Override
	public Collection<String> getColumns () {
		return this.columns;
	}

}
