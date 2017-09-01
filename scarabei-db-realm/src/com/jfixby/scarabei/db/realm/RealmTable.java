
package com.jfixby.scarabei.db.realm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Sequence;
import com.jfixby.scarabei.api.db.Entry;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.db.TableSchema;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.strings.Strings;

import io.realm.Realm;
import io.realm.RealmResults;

class RealmTable implements Table {

	final RealmDataBase db;
	final String sql_table_name;
	private final RealmTableSchema schema;

	public RealmTable (final RealmDataBase mySQL, final String name) throws IOException {
		this.db = mySQL;
		this.sql_table_name = name;
		this.schema = new RealmTableSchema(this);

	}

	@Override
	public List<Entry> listAll () throws IOException {
		final Realm connection = this.db.obtainConnection();

		CallsFo

		final RealmResults<RealmDBEntry> result = connection.where(RealmDBEntry.class).findAll();
		final List<Entry> resultList = this.collectResult(result);
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
		return new RealmEntry();
	}

	@Override
	public TableSchema getSchema () {
		return this.schema;
	}

	private String paramString (final Entry entry, final List<String> keys, final String bracketLeft, final String bracketRight)
		throws IOException {
		final TableSchema schema = this.getSchema();
		final Collection<String> colums = schema.getColumns();

		for (int i = 0; i < colums.size(); i++) {
			final String key = colums.getElementAt(i);
			final String value = entry.getValue(key);
			if (value != null) {
				keys.add(key);
			}
		}
		final String schemaString = Strings.wrapSequence(keys, keys.size(), bracketLeft, bracketRight, ", ");

		return schemaString + " VALUES " + Strings.wrapSequence(new Sequence<String>() {
			@Override
			public String getElementAt (final long i) {
				return "?";
			}
		}, keys.size(), "(", ")", ", ");
	}

	@Override
	public void clear () throws IOException {
		L.d("clear sql table", this.sql_table_name);
		final String request = "TRUNCATE " + this.sql_table_name;
		final Realm connection = this.db.obtainConnection();
		connection.delete(clazz);
		connection.checkIsOpen();
		try {
			final Connection mysql_connection = connection.getConnection();
			final PreparedStatement statement = mysql_connection.prepareStatement(request);
			statement.execute();
			L.d("         ", "done");
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}
	}

	@Override
	public void replaceEntries (final List<Entry> batch) throws IOException {
		if (batch.size() == 0) {
			return;
		}
		final Entry entry0 = batch.getElementAt(0);
		final String table_name = this.sql_table_name;
		final List<String> keys = Collections.newList();
		final String stm = "REPLACE " + table_name + " " + this.paramString(entry0, keys, "(", ")");
		final RealmConnection connection = this.db.obtainConnection();
		connection.checkIsOpen();
		try {
			final Connection mysql_connection = connection.getConnection();
			final PreparedStatement statement = mysql_connection.prepareStatement(stm);
			for (int b = 0; b < batch.size(); b++) {
				final Entry entry = batch.getElementAt(b);
				for (int i = 0; i < keys.size(); i++) {
					final String key = keys.getElementAt(i);
					final String value = entry.getValue(key);
					statement.setString(i + 1, value);
				}
				statement.addBatch();
			}
			statement.executeBatch();
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}
	}

	@Override
	public void addEntries (final Collection<Entry> batch) throws IOException {
		if (batch.size() == 0) {
			return;
		}
		final Entry entry0 = batch.getElementAt(0);
		final String table_name = this.sql_table_name;
		final List<String> keys = Collections.newList();
		final String stm = "INSERT INTO " + table_name + " " + this.paramString(entry0, keys, "(", ")");
		final RealmConnection connection = this.db.obtainConnection();
		connection.checkIsOpen();
		try {
			final Connection mysql_connection = connection.getConnection();

			final PreparedStatement statement = mysql_connection.prepareStatement(stm);
			for (int b = 0; b < batch.size(); b++) {
				final Entry entry = batch.getElementAt(b);
				for (int i = 0; i < keys.size(); i++) {
					final String key = keys.getElementAt(i);
// final String value = entry.getValue(key);
					final String value = entry.getValue(key);
					statement.setString(i + 1, value);
				}
				statement.addBatch();
			}
			statement.executeBatch();
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}
	}

	@Override
	public void addEntry (final Entry entry) throws IOException {

		final String table_name = this.sql_table_name;
		final List<String> keys = Collections.newList();
		final String stm = "INSERT INTO " + table_name + " " + this.paramString(entry, keys, "(", ")");
		final RealmConnection connection = this.db.obtainConnection();
		connection.checkIsOpen();
		try {
			final Connection mysql_connection = connection.getConnection();

			final PreparedStatement statement = mysql_connection.prepareStatement(stm);

			for (int i = 0; i < keys.size(); i++) {
				final String key = keys.getElementAt(i);
				final String value = entry.getValue(key);
				statement.setString(i + 1, value);
			}

			statement.execute();
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}
	}

	@Override
	public boolean deleteEntry (final TableSchema schema, final int keyIndex, final Object value) throws IOException {
		final String key = this.schema.getColumns().getElementAt(keyIndex);
		Debug.checkNull("value", value);

		final RealmConnection connection = this.db.obtainConnection();
		connection.checkIsOpen();
		try {
			final Connection mysql_connection = connection.getConnection();

			final String table_name = this.sql_table_name;
			final String stm = "DELETE * FROM " + table_name + " WHERE " + key + " = ?";
			final PreparedStatement statement = mysql_connection//
				.prepareStatement(stm);
			statement.setString(1, value + "");
			return statement.execute();
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}
	}

	@Override
	public Collection<Entry> findEntries (final TableSchema schema, final int keyIndex, final Object value) throws IOException {
		final String key = this.schema.getColumns().getElementAt(keyIndex);
		Debug.checkNull("value", value);

		final RealmConnection connection = this.db.obtainConnection();
		connection.checkIsOpen();
		try {
			final Connection mysql_connection = connection.getConnection();

			final String table_name = this.sql_table_name;
			final String stm = "SELECT * FROM " + table_name + " WHERE " + key + " = ?";
			final PreparedStatement statement = mysql_connection//
				.prepareStatement(stm);
			statement.setString(1, value + "");
			final ResultSet result = statement.executeQuery();
			final List<Entry> res = this.collectResult(result);
			return res;
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}
	}

	@Override
	public boolean deleteEntry (final Entry entry) throws IOException {
		final Collection<String> shema = this.schema.getColumns();

		final List<String> keys = Collections.newList();
		this.paramString(entry, keys, "(", ")");
		final String table_name = this.sql_table_name;
		final String stm;
		if (keys.size() == 0) {
			stm = "DELETE FROM " + table_name;
		} else {
			stm = "DELETE FROM " + table_name + " WHERE " + Strings.wrapSequence(new Sequence<String>() {
				@Override
				public String getElementAt (final long i) {
					return keys.getElementAt(i) + "=" + "?";
				}
			}, keys.size(), "", "", " AND ");
		}

		final RealmConnection connection = this.db.obtainConnection();
		connection.checkIsOpen();
		try {
			final Connection mysql_connection = connection.getConnection();
			final PreparedStatement statement = mysql_connection//
				.prepareStatement(stm);
			int k = 1;
			for (int i = 0; i < keys.size(); i++) {
				final String valuei = entry.getValue(keys.getElementAt(i));
				statement.setString(k, valuei);
				k++;
			}

			return statement.execute();
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}

	}

	@Override
	public void deleteEntries (final Collection<Entry> paramEntries) throws IOException {
		for (final Entry e : paramEntries) {// fuck you
			this.deleteEntry(e);
		}

	}

}
