
package com.jfixby.scarabei.red.db.stupid;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.db.Entry;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.db.TableSchema;
import com.jfixby.scarabei.api.names.ID;

public class StupidTable implements Table {
	final StupidDB db;
	final ID tableName;
	private final StupidTableSchema schema;

	final List<Entry> entries = Collections.newList();

	StupidTable (final StupidDB stupidDB, final ID tableName) {
		this.db = stupidDB;
		this.tableName = tableName;
		this.schema = new StupidTableSchema(this);
	}

	@Override
	public Entry newEntry () {
		return new StupidEntry(this);
	}

	@Override
	public TableSchema getSchema () throws IOException {
		return this.schema;
	}

	@Override
	public Collection<Entry> listAll () throws IOException {
		return this.entries.copy();
	}

	@Override
	public void addEntry (final Entry entry) throws IOException {
		this.entries.add(this.copyValues(entry));
	}

	@Override
	public void addEntries (final Collection<Entry> batch) throws IOException {
		for (final Entry entry : batch) {
			this.addEntry(entry);
		}
	}

	@Override
	public void clear () throws IOException {
		this.entries.clear();
	}

	@Override
	public void replaceEntries (final List<Entry> batch) throws IOException {
		for (final Entry newe : batch) {
			for (final Entry stored : this.entries) {
				if (this.isTheSame(newe, stored)) {
					this.copyValues(newe, stored);
				}
			}
		}
	}

	private Entry copyValues (final Entry from, final Entry to) {
		final Collection<String> vars = this.schema.getColumns();
		for (final String key : vars) {
			final String val = from.getValue(key);
			to.set(this.schema, this.schema.indexOf(key), val);
		}
		return to;
	}

	private Entry copyValues (final Entry from) {
		return this.copyValues(from, this.newEntry());
	}

	@Override
	public Collection<Entry> findEntries (final TableSchema schema, final int keyIndex, final Object value) throws IOException {
		final List<Entry> result = Collections.newList();
		final String key = this.schema.getColumns().getElementAt(keyIndex);
		for (final Entry e : this.entries) {
			final String val = e.getValue(key);
			if (val.equals(this.toString(value))) {
				result.add(this.copyValues(e));
			}
		}
		return result;
	}

	@Override
	public boolean deleteEntry (final TableSchema schema, final int keyIndex, final Object value) throws IOException {
		final Collection<Entry> toDelete = this.findEntries(schema, keyIndex, value);
		if (toDelete.size() == 0) {
			return false;
		}
		this.deleteEntries(toDelete);
		return true;
	}

	public static String toString (final Object value) {
		return value == null ? null : value.toString();
	}

	@Override
	public boolean deleteEntry (final Entry entry) throws IOException {
		for (final Entry e : this.entries) {
			if (this.isTheSame(e, entry)) {
				this.entries.remove(e);
				return true;
			}
		}
		return false;
	}

	private boolean isTheSame (final Entry x, final Entry y) {
		return x.equals(y);
	}

	@Override
	public void deleteEntries (final Collection<Entry> paramEntries) throws IOException {
		for (final Entry e : paramEntries) {
			this.deleteEntry(e);
		}
	}

	@Override
	public ID getName () {
		return this.tableName;
	}
}
