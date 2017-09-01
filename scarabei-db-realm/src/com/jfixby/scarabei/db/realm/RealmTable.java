
package com.jfixby.scarabei.db.realm;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.db.Entry;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.db.TableSchema;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;

import io.realm.Realm;
import io.realm.RealmResults;

class RealmTable implements Table {

	final RealmDataBase db;
	final ID sql_table_name;
	private final RealmTableSchema schema;
	final Class<? extends RealmDBEntry> clazz;
	private final Realm connection;

	public RealmTable (final RealmDataBase mySQL, final ID name) throws IOException {
		this.db = mySQL;
		this.sql_table_name = name;
		this.schema = new RealmTableSchema(this);
		try {
			this.clazz = this.db.classforName(this.sql_table_name);
		} catch (final ClassNotFoundException e) {
// e.printStackTrace();
			throw new IOException(e);
		}
		this.connection = this.db.obtainConnection();
	}

	@Override
	public List<Entry> listAll () {

		final RealmResults<? extends RealmDBEntry> result = this.connection.where(this.clazz).findAll();
		final List<Entry> resultList = this.collectResult((RealmResults<RealmDBEntry>)result);
		return resultList;

	}

	private List<Entry> collectResult (final RealmResults<RealmDBEntry> list) {
		final List<Entry> entries = Collections.newList();
		final TableSchema schema = this.getSchema();
		final Collection<String> columns = schema.getColumns();
		for (final RealmDBEntry e : list) {
			final Entry entry = this.readEntry(e, columns);
			entries.add(entry);
		}

		return entries;

	}

	private Entry readEntry (final RealmDBEntry dbEntry, final Collection<String> columns) {
		final RealmEntry entry = this.newEntry();
		final int N = columns.size();
		for (int i = 0; i < N; i++) {
			final String key = columns.getElementAt(i);
			final String value = dbEntry.getString(i);
			entry.set(this.schema, this.schema.indexOf(key), value);
		}
		return entry;
	}

	@Override
	public RealmEntry newEntry () {
		return new RealmEntry(this);
	}

	@Override
	public TableSchema getSchema () {
		return this.schema;
	}

	@Override
	public void clear () {
		L.d("clear table", this.sql_table_name);
		this.connection.delete(this.clazz);
	}

	@Override
	public void replaceEntries (final List<Entry> batch) {
		if (batch.size() == 0) {
			return;
		}
		Err.throwNotImplementedYet();
	}

	@Override
	public void addEntries (final Collection<Entry> batch) {
		for (final Entry e : batch) {
			this.addEntry(e);
		}
	}

	private RealmDBEntry toRealmDBEntry (final Entry e) {
		final RealmDBEntry result = new RealmDBEntry();
		for (final String c : this.schema.getColumns()) {
			result.values.add(e.getValue(c));
		}
		return result;
	}

	@Override
	public void addEntry (final Entry entry) {
		final RealmDBEntry realmDBentry = this.toRealmDBEntry(entry);
		this.connection.insert(realmDBentry);
	}

	@Override
	public boolean deleteEntry (final TableSchema schema, final int keyIndex, final Object value) {
		Debug.checkNull("value", value);

		final String key = this.schema.getColumns().getElementAt(keyIndex);

		this.connection.beginTransaction();
		final RealmResults<? extends RealmDBEntry> list = this.connection.where(this.clazz).equalTo(key, this.toString(value))
			.findAll();
		final boolean delete = list.deleteFirstFromRealm();
		this.connection.commitTransaction();
		return delete;
	}

	public static String toString (final Object value) {
		if (value == null) {
			return null;
		}
		return value.toString();
	}

	@Override
	public Collection<Entry> findEntries (final TableSchema schema, final int keyIndex, final Object value) {
		Debug.checkNull("value", value);

		final String key = this.schema.getColumns().getElementAt(keyIndex);

		this.connection.beginTransaction();
		final RealmResults<? extends RealmDBEntry> list = this.connection.where(this.clazz).equalTo(key, this.toString(value))
			.findAll();
		final List<Entry> resultList = this.collectResult((RealmResults<RealmDBEntry>)list);
		return resultList;

	}

	@Override
	public boolean deleteEntry (final Entry entry) {
		Debug.checkNull("entry", entry);
//
// final String key = this.schema.getColumns().getElementAt(keyIndex);
//
// this.connection.beginTransaction();
// final RealmResults<? extends RealmDBEntry> list = this.connection.where(this.clazz).equalTo(key, this.toString(value))
// .findAll();
// final boolean delete = list.deleteFirstFromRealm();
// this.connection.commitTransaction();
// return delete;
		Err.throwNotImplementedYet();
		return false;
	}

	@Override
	public void deleteEntries (final Collection<Entry> paramEntries) {
		for (final Entry e : paramEntries) {// fuck you
			this.deleteEntry(e);
		}

	}

	@Override
	public ID getName () {
		return this.sql_table_name;
	}

}
