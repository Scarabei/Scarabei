
package com.jfixby.cmns.db.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;

public class MySQLTable {

	final MySQL db;
	final String sql_table_name;
	final MySQLTableSchema schema = new MySQLTableSchema(this);

	public MySQLTable (final MySQL mySQL, final String name) {
		this.db = mySQL;
		this.sql_table_name = name;

	}

	public List<MySQLEntry> listAll () throws SQLException {

		final Connection connection = this.connection();
		final Statement statement = connection.createStatement();
		final String request = "SELECT * FROM " + this.sql_table_name;
		final ResultSet result = statement.executeQuery(request);

		return this.collectResult(result);
	}

	private List<MySQLEntry> collectResult (final ResultSet result) throws SQLException {
		final List<MySQLEntry> entries = Collections.newList();

		while (result.next()) {
			final MySQLEntry entry = this.readEntry(result, this.schema);
			entries.add(entry);
		}

		return entries;
	}

	Connection connection () throws SQLException {
		return this.db.connection().getConnection();
	}

	private MySQLEntry readEntry (final ResultSet result, final MySQLTableSchema schema) throws SQLException {
		final MySQLEntry entry = new MySQLEntry();
		final int N = schema.getColumns().size();
		for (int i = 0; i < N; i++) {
			final String key = schema.getColumns().getElementAt(i);
			final String value = result.getString(i + 1);
			entry.set(key, value);
		}
		return entry;
	}

	public MySQLEntry newMySQLEntry () {
		return new MySQLEntry();
	}

	public MySQLTableSchema getSchema () throws SQLException {
		return this.schema.loadIfNotLoaded();
	}

	private String paramString (final MySQLEntry entry, final List<String> keys, final String bracketLeft,
		final String bracketRight) throws SQLException {
		this.schema.loadIfNotLoaded();
		final Collection<String> colums = this.schema.getColumns();

		for (int i = 0; i < colums.size(); i++) {
			final String key = colums.getElementAt(i);
			final String value = entry.values.get(key);
			if (value != null) {
				keys.add(key);
			}
		}
		final String schemaString = JUtils.wrapSequence(keys, keys.size(), bracketLeft, bracketRight);

		return schemaString + " VALUES " + JUtils.wrapSequence( (i) -> "?", keys.size(), "(", ")");
	}

	public Collection<MySQLEntry> findEntries (final String key, final String value) throws SQLException {
		Debug.checkNull("key", key);
		Debug.checkNull("value", value);
		final String table_name = this.sql_table_name;
		final String stm = "SELECT * FROM " + table_name + " WHERE " + key + " = ?";
		final PreparedStatement statement = this.connection()//
			.prepareStatement(stm);
		statement.setString(1, value);
		final ResultSet result = statement.executeQuery();
		return this.collectResult(result);
	}

	public void clear () throws SQLException {
		L.d("clear sql table", this.sql_table_name);
		final String request = "TRUNCATE " + this.sql_table_name;
		final PreparedStatement statement = this.connection().prepareStatement(request);
		statement.execute();
		L.d("         ", "done");
	}

// public void updateEntries (final List<MySQLEntry> batch) throws SQLException {
//
// if (batch.size() == 0) {
// return;
// }
// final MySQLEntry entry0 = batch.getElementAt(0);
// final String table_name = this.sql_table_name;
// final List<String> keys = Collections.newList();
//
// this.schema.loadIfNotLoaded();
// final Collection<String> colums = this.schema.getColumns();
//
// for (int i = 0; i < colums.size(); i++) {
// final String key = colums.getElementAt(i);
// final String value = entry0.values.get(key);
// if (value != null) {
// keys.add(key);
// }
// }
// final String schemaString = JUtils.wrapSequence(i -> keys.getElementAt(i) + "=?", keys.size(), "", "");
//
// final String stm = "UPDATE " + table_name + " SET " + schemaString + " WHERE " + schemaString;
// L.d(stm);
// Sys.exit();
//
// final PreparedStatement statement = this.connection().prepareStatement(stm);
// for (int b = 0; b < batch.size(); b++) {
// final MySQLEntry entry = batch.getElementAt(b);
// int arg = 0;
// for (int i = 0; i < keys.size(); i++, arg++) {
// final String key = keys.getElementAt(i);
// final String value = entry.values.get(key);
// statement.setString(arg + 1, value);
// }
// for (int i = 0; i < keys.size(); i++, arg++) {
// final String key = keys.getElementAt(i);
// final String value = entry.values.get(key);
// statement.setString(arg + 1, value);
// }
// statement.addBatch();
// }
// statement.executeBatch();
//
// }

	public void replaceEntries (final List<MySQLEntry> batch) throws SQLException {
		if (batch.size() == 0) {
			return;
		}
		final MySQLEntry entry0 = batch.getElementAt(0);
		final String table_name = this.sql_table_name;
		final List<String> keys = Collections.newList();
		final String stm = "REPLACE " + table_name + " " + this.paramString(entry0, keys, "(", ")");
// L.d(stm);

		final PreparedStatement statement = this.connection().prepareStatement(stm);
		for (int b = 0; b < batch.size(); b++) {
			final MySQLEntry entry = batch.getElementAt(b);
			for (int i = 0; i < keys.size(); i++) {
				final String key = keys.getElementAt(i);
				final String value = entry.values.get(key);
				statement.setString(i + 1, value);
			}
			statement.addBatch();
		}
		statement.executeBatch();
	}

	public void addEntries (final Collection<MySQLEntry> batch) throws SQLException {
		if (batch.size() == 0) {
			return;
		}
		final MySQLEntry entry0 = batch.getElementAt(0);
		final String table_name = this.sql_table_name;
		final List<String> keys = Collections.newList();
		final String stm = "INSERT INTO " + table_name + " " + this.paramString(entry0, keys, "(", ")");
// L.d(stm);

		final PreparedStatement statement = this.connection().prepareStatement(stm);
		for (int b = 0; b < batch.size(); b++) {
			final MySQLEntry entry = batch.getElementAt(b);
			for (int i = 0; i < keys.size(); i++) {
				final String key = keys.getElementAt(i);
				final String value = entry.values.get(key);
				statement.setString(i + 1, value);
			}
			statement.addBatch();
		}
		statement.executeBatch();
	}

	public void addEntry (final MySQLEntry entry) throws SQLException {

		final String table_name = this.sql_table_name;
		final List<String> keys = Collections.newList();
		final String stm = "INSERT INTO " + table_name + " " + this.paramString(entry, keys, "(", ")");
// L.d(stm);

		final PreparedStatement statement = this.connection().prepareStatement(stm);
		for (int i = 0; i < keys.size(); i++) {
			final String key = keys.getElementAt(i);
			final String value = entry.values.get(key);
			statement.setString(i + 1, value);
		}

		statement.execute();
	}

}