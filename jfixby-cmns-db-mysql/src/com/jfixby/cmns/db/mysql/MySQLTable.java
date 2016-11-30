
package com.jfixby.cmns.db.mysql;

import java.io.IOException;
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

	public List<MySQLEntry> listAll () throws IOException {
		final MySQLConnection connection = this.db.obtainConnection();

		try {
			final Connection mysql_connection = connection.getConnection();

			final Statement statement = mysql_connection.createStatement();
			final String request = "SELECT * FROM " + this.sql_table_name;
			final ResultSet result = statement.executeQuery(request);

			final List<MySQLEntry> resultList = this.collectResult(result);

			return resultList;
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}
	}

	private List<MySQLEntry> collectResult (final ResultSet result) throws SQLException, IOException {
		final List<MySQLEntry> entries = Collections.newList();
		final Collection<String> columns = this.schema.getColumns();
		while (result.next()) {
			final MySQLEntry entry = this.readEntry(result, columns);
			entries.add(entry);
		}

		return entries;

	}

	private MySQLEntry readEntry (final ResultSet result, final Collection<String> columns) throws SQLException {
		final MySQLEntry entry = new MySQLEntry();

		final int N = columns.size();
		for (int i = 0; i < N; i++) {
			final String key = columns.getElementAt(i);
			final String value = result.getString(i + 1);
			entry.set(key, value);
		}
		return entry;
	}

	public MySQLEntry newMySQLEntry () {
		return new MySQLEntry();
	}

	public MySQLTableSchema getSchema () throws IOException {
		return this.schema.loadIfNotLoaded();
	}

	private String paramString (final MySQLEntry entry, final List<String> keys, final String bracketLeft,
		final String bracketRight) throws IOException {
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

	public Collection<MySQLEntry> findEntries (final String key, final String value) throws IOException {
		Debug.checkNull("key", key);
		Debug.checkNull("value", value);

		final MySQLConnection connection = this.db.obtainConnection();
		try {
			final Connection mysql_connection = connection.getConnection();

			final String table_name = this.sql_table_name;
			final String stm = "SELECT * FROM " + table_name + " WHERE " + key + " = ?";
			final PreparedStatement statement = mysql_connection//
				.prepareStatement(stm);
			statement.setString(1, value);
			final ResultSet result = statement.executeQuery();
			final List<MySQLEntry> res = this.collectResult(result);
			return res;
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}
	}

	public void clear () throws IOException {
		L.d("clear sql table", this.sql_table_name);
		final String request = "TRUNCATE " + this.sql_table_name;
		final MySQLConnection connection = this.db.obtainConnection();
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

	public void replaceEntries (final List<MySQLEntry> batch) throws IOException {
		if (batch.size() == 0) {
			return;
		}
		final MySQLEntry entry0 = batch.getElementAt(0);
		final String table_name = this.sql_table_name;
		final List<String> keys = Collections.newList();
		final String stm = "REPLACE " + table_name + " " + this.paramString(entry0, keys, "(", ")");
		final MySQLConnection connection = this.db.obtainConnection();
		try {
			final Connection mysql_connection = connection.getConnection();
			final PreparedStatement statement = mysql_connection.prepareStatement(stm);
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
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}
	}

	public void addEntries (final Collection<MySQLEntry> batch) throws IOException {
		if (batch.size() == 0) {
			return;
		}
		final MySQLEntry entry0 = batch.getElementAt(0);
		final String table_name = this.sql_table_name;
		final List<String> keys = Collections.newList();
		final String stm = "INSERT INTO " + table_name + " " + this.paramString(entry0, keys, "(", ")");
		final MySQLConnection connection = this.db.obtainConnection();
		try {
			final Connection mysql_connection = connection.getConnection();

			final PreparedStatement statement = mysql_connection.prepareStatement(stm);
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
		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.db.releaseConnection(connection);
		}
	}

	public void addEntry (final MySQLEntry entry) throws IOException {

		final String table_name = this.sql_table_name;
		final List<String> keys = Collections.newList();
		final String stm = "INSERT INTO " + table_name + " " + this.paramString(entry, keys, "(", ")");
		final MySQLConnection connection = this.db.obtainConnection();
		try {
			final Connection mysql_connection = connection.getConnection();

			final PreparedStatement statement = mysql_connection.prepareStatement(stm);

			for (int i = 0; i < keys.size(); i++) {
				final String key = keys.getElementAt(i);
				final String value = entry.values.get(key);
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

}
