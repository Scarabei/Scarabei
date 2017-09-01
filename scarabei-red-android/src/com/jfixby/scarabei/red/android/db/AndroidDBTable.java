
package com.jfixby.scarabei.red.android.db;

import java.io.IOException;

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

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AndroidDBTable implements Table {

	final String sql_table_name;
	final AndroidDB db;
	private final AndroidDBTableSchema schema;

	public AndroidDBTable (final AndroidDB androidDB, final String tableName) throws IOException {
		this.db = androidDB;
		this.sql_table_name = tableName;
		this.schema = new AndroidDBTableSchema(this);
	}

	@Override
	public List<Entry> listAll () throws IOException {
		final AndroidDBHelper connection = this.db.obtainConnection();
		final SQLiteDatabase db = connection.getReadableDatabase();
		final String request = "SELECT * FROM " + this.sql_table_name;
		final Cursor cur = db.rawQuery(request, null);
		final List<Entry> resultList = this.collectResult(cur);
		this.db.releaseConnection(connection);
		return resultList;

	}

	private List<Entry> collectResult (final Cursor cursor) {
		final List<Entry> entries = Collections.newList();
		final TableSchema schema = this.getSchema();
		final Collection<String> columns = schema.getColumns();

		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			do {
				final Entry entry = this.readEntry(cursor, columns);
				entries.add(entry);
			} while (cursor.moveToNext());
		}

		return entries;

	}

	private Entry readEntry (final Cursor cursor, final Collection<String> columns) {
		final Entry entry = this.newEntry();
		final int N = columns.size();
		for (int i = 0; i < N; i++) {
			final String key = columns.getElementAt(i);
			final String value = cursor.getString(i);
			entry.set(this.schema, this.schema.indexOf(key), value);
		}
		return entry;
	}

	@Override
	public Entry newEntry () {
		return new AndroidDBEntry();
	}

	@Override
	public AndroidDBTableSchema getSchema () {
		return this.schema;
	}

	private String paramString (final Entry entry, final List<String> keys, final String bracketLeft, final String bracketRight)
		throws IOException {
		final AndroidDBTableSchema schema = this.getSchema();
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

		final AndroidDBHelper connection = this.db.obtainConnection();
		final SQLiteDatabase db = connection.getReadableDatabase();
		final String request = "TRUNCATE " + this.sql_table_name;
		final Cursor cur = db.rawQuery(request, null);
		this.db.releaseConnection(connection);

	}

	@Override
	public void replaceEntries (final List<Entry> batch) throws IOException {
		if (batch.size() == 0) {
			return;
		}
// final Entry entry0 = batch.getElementAt(0);
// final String table_name = this.sql_table_name;
// final List<String> keys = Collections.newList();
// final String stm = "REPLACE " + table_name + " " + this.paramString(entry0, keys, "(", ")");
// final AndroidDBConnection connection = this.db.obtainConnection();
// connection.checkIsOpen();
// try {
// final SQLExecutor mysql_connection = connection.getConnection();
// final PreparedStatement statement = mysql_connection.prepareStatement(stm);
// for (int b = 0; b < batch.size(); b++) {
// final Entry entry = batch.getElementAt(b);
// for (int i = 0; i < keys.size(); i++) {
// final String key = keys.getElementAt(i);
// final String value = entry.getValue(key);
// statement.setString(i + 1, value);
// }
// statement.addBatch();
// }
// statement.executeBatch();
// } catch (final SQLException e) {
// e.printStackTrace();
// throw new IOException(e);
// } finally {
// this.db.releaseConnection(connection);
// }
	}

	@Override
	public void addEntries (final Collection<Entry> batch) throws IOException {
		if (batch.size() == 0) {
			return;
		}
// final Entry entry0 = batch.getElementAt(0);
// final String table_name = this.sql_table_name;
// final List<String> keys = Collections.newList();
// final String stm = "INSERT INTO " + table_name + " " + this.paramString(entry0, keys, "(", ")");
// final AndroidDBConnection connection = this.db.obtainConnection();
// connection.checkIsOpen();
// try {
// final SQLExecutor mysql_connection = connection.getConnection();
//
// final PreparedStatement statement = mysql_connection.prepareStatement(stm);
// for (int b = 0; b < batch.size(); b++) {
// final Entry entry = batch.getElementAt(b);
// for (int i = 0; i < keys.size(); i++) {
// final String key = keys.getElementAt(i);
//// final String value = entry.getValue(key);
// final String value = entry.getValue(key);
// statement.setString(i + 1, value);
// }
// statement.addBatch();
// }
// statement.executeBatch();
// } catch (final SQLException e) {
// e.printStackTrace();
// throw new IOException(e);
// } finally {
// this.db.releaseConnection(connection);
// }
	}

	@Override
	public void addEntry (final Entry entry) throws IOException {

// final String table_name = this.sql_table_name;
// final List<String> keys = Collections.newList();
// final String stm = "INSERT INTO " + table_name + " " + this.paramString(entry, keys, "(", ")");
// final AndroidDBConnection connection = this.db.obtainConnection();
// connection.checkIsOpen();
// try {
// final SQLExecutor mysql_connection = connection.getConnection();
//
// final PreparedStatement statement = mysql_connection.prepareStatement(stm);
//
// for (int i = 0; i < keys.size(); i++) {
// final String key = keys.getElementAt(i);
// final String value = entry.getValue(key);
// statement.setString(i + 1, value);
// }
//
// statement.execute();
// } catch (final SQLException e) {
// e.printStackTrace();
// throw new IOException(e);
// } finally {
// this.db.releaseConnection(connection);
// }
	}

	@Override
	public boolean deleteEntry (final TableSchema schema, final int keyIndex, final Object value) throws IOException {
		final String key = this.schema.getColumns().getElementAt(keyIndex);
		Debug.checkNull("value", value);
		return false;

// final AndroidDBConnection connection = this.db.obtainConnection();
// connection.checkIsOpen();
// try {
// final SQLExecutor mysql_connection = connection.getConnection();
//
// final String table_name = this.sql_table_name;
// final String stm = "DELETE * FROM " + table_name + " WHERE " + key + " = ?";
// final PreparedStatement statement = mysql_connection//
// .prepareStatement(stm);
// statement.setString(1, value + "");
// return statement.execute();
// } catch (final SQLException e) {
// e.printStackTrace();
// throw new IOException(e);
// } finally {
// this.db.releaseConnection(connection);
// }
	}

	@Override
	public Collection<Entry> findEntries (final TableSchema schema, final int keyIndex, final Object value) throws IOException {
		return null;
// final String key = this.schema.getColumns().getElementAt(keyIndex);
// Debug.checkNull("value", value);
//
// final AndroidDBConnection connection = this.db.obtainConnection();
// connection.checkIsOpen();
// try {
// final SQLExecutor mysql_connection = connection.getConnection();
//
// final String table_name = this.sql_table_name;
// final String stm = "SELECT * FROM " + table_name + " WHERE " + key + " = ?";
// final PreparedStatement statement = mysql_connection//
// .prepareStatement(stm);
// statement.setString(1, value + "");
// final ResultSet result = statement.executeQuery();
// final List<Entry> res = this.collectResult(result);
// return res;
// } catch (final SQLException e) {
// e.printStackTrace();
// throw new IOException(e);
// } finally {
// this.db.releaseConnection(connection);
// }
	}

	@Override
	public boolean deleteEntry (final Entry entry) throws IOException {
		final Collection<String> shema = this.schema.getColumns();
//
// final List<String> keys = Collections.newList();
// this.paramString(entry, keys, "(", ")");
// final String table_name = this.sql_table_name;
// final String stm;
// if (keys.size() == 0) {
// stm = "DELETE FROM " + table_name;
// } else {
// stm = "DELETE FROM " + table_name + " WHERE " + Strings.wrapSequence(new Sequence<String>() {
// @Override
// public String getElementAt (final long i) {
// return keys.getElementAt(i) + "=" + "?";
// }
// }, keys.size(), "", "", " AND ");
// }
//
// final AndroidDBConnection connection = this.db.obtainConnection();
// connection.checkIsOpen();
// try {
// final SQLExecutor mysql_connection = connection.getConnection();
// final PreparedStatement statement = mysql_connection//
// .prepareStatement(stm);
// int k = 1;
// for (int i = 0; i < keys.size(); i++) {
// final String valuei = entry.getValue(keys.getElementAt(i));
// statement.setString(k, valuei);
// k++;
// }
//
// return statement.execute();
// } catch (final SQLException e) {
// e.printStackTrace();
// throw new IOException(e);
// } finally {
// this.db.releaseConnection(connection);
// }
		return false;

	}

	@Override
	public void deleteEntries (final Collection<Entry> paramEntries) throws IOException {
		for (final Entry e : paramEntries) {// fuck you
			this.deleteEntry(e);
		}

	}

}
