
package com.jfixby.scarabei.red.db.stupid;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.db.Entry;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.db.TableSchema;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;

public class StupidTable implements Table {
	final StupidDB db;
	final String tableName;
	private final StupidTableSchema schema;

	final List<Entry> entries = Collections.newList();
	private final File storageFile;

	StupidTable (final StupidDB stupidDB, final String tableName) throws IOException {
		this.db = stupidDB;
		this.tableName = tableName;
		final String fileName = this.db.getDBName().child(tableName).child("json").toString();
		this.storageFile = this.db.storageFolder.child(fileName);
		if (this.storageFile.exists()) {
			this.readStorage();
		} else {
			this.writeStorage();
		}
		this.schema = new StupidTableSchema(this);
	}

	private void writeStorage () throws IOException {
		final SrlzdTable table = new SrlzdTable();
		for (final Entry e : this.entries) {
			table.entries.add(this.srlzEntry(e));
		}
		final JsonString json = Json.serializeToString(table);
		this.storageFile.writeString(json.toString());
	}

	private SrlzdEntry srlzEntry (final Entry e) {
		final SrlzdEntry s = new SrlzdEntry();
		for (final String key : this.schema.columns) {
			s.values.put(key, e.getValue(key));
		}
		return s;
	}

	private void readStorage () {
	}

	@Override
	public Entry newEntry () {
		return new StupidEntry(this);
	}

	@Override
	public TableSchema getSchema () {
		return this.schema;
	}

	@Override
	public Collection<Entry> listAll () throws IOException {
		return this.entries.copy();
	}

	@Override
	public void addEntry (final Entry entry) throws IOException {
		this.entries.add(this.copyValues(entry));
		this.writeStorage();
	}

	@Override
	public void addEntries (final Collection<Entry> batch) throws IOException {
		for (final Entry entry : batch) {
			this.entries.add(this.copyValues(entry));
		}
		this.writeStorage();
	}

	@Override
	public void clear () throws IOException {
		this.entries.clear();
		this.writeStorage();
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
		this.writeStorage();
	}

	private Entry copyValues (final Entry from, final Entry to) {
		final Collection<String> vars = this.schema.getColumns();
		for (final String key : vars) {
			final Object val = from.getValue(key);
			to.setValue(key, val);
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
			final Object val = e.getValue(key);
			if (val.equals(value)) {
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

	@Override
	public boolean deleteEntry (final Entry entry) throws IOException {
		for (final Entry e : this.entries) {
			if (this.isTheSame(e, entry)) {
				this.entries.remove(e);
				this.writeStorage();
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
		for (final Entry d : paramEntries) {
			for (final Entry e : this.entries) {
				if (this.isTheSame(e, d)) {
					this.entries.remove(e);
					break;
				}
			}
		}
		this.writeStorage();
	}

	@Override
	public String getName () {
		return this.tableName;
	}
}
