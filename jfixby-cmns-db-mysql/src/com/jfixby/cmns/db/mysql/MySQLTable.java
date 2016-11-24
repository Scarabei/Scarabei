
package com.jfixby.cmns.db.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.log.L;

public class MySQLTable {

	final MySQL db;
	final String sql_table_name;
	final MySQLTableSchema schema = new MySQLTableSchema(this);

	public MySQLTable (final MySQL mySQL, final String name) {
		this.db = mySQL;
		this.sql_table_name = name;

	}

	public List<MySQLEntry> listAll () throws SQLException {
		final List<MySQLEntry> entries = Collections.newList();
		final Connection connection = this.connection();
		final Statement statement = connection.createStatement();
		final String request = "SELECT * FROM " + this.sql_table_name;
		final ResultSet result = statement.executeQuery(request);
		while (result.next()) {
			final MySQLEntry entry = this.readEntry(result, this.schema);
			entries.add(entry);
		}

		return entries;
	}

	Connection connection () {
		return this.db.connection().getConnection();
	}

	private MySQLEntry readEntry (final ResultSet result, final MySQLTableSchema schema) throws SQLException {
		final MySQLEntry entry = new MySQLEntry();
		final int N = schema.columns.size();
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

	public void addEntry (final MySQLEntry entry) throws SQLException {
		this.schema.loadIfNotLoaded();
		final Collection<String> colums = this.schema.getColumns();
// colums.print("colums");

// final String schemaString = this.schemaString(Collections.newList("regID", "c", "d"));
		final String schemaString = this.schemaString(Collections.newList(colums));

// final String stm = "INSERT INTO " + this.sql_table_name + "" + schemaString + " " + "VALUES" + ;
		final String table_name = this.sql_table_name;
		final String stm = "INSERT INTO " + table_name + " " + schemaString + " VALUES " + this.QString(colums.size());
		L.d(stm);
		final PreparedStatement statement = this.connection().prepareStatement(stm);
		for (int i = 0; i < entry.values.size(); i++) {
			final String key = colums.getElementAt(i);
			statement.setString(i + 1, entry.values.get(key));
// if (i == 1) {
// break;
// }
		}

		statement.executeUpdate();
	}

	private String QString (final int size) {
		final int iMax = size - 1;
		if (iMax == -1) {
			return "()";
		}

		final StringBuilder b = new StringBuilder();
		b.append('(');
		for (int i = 0;; i++) {
			b.append("?");
			if (i == iMax) {
				return b.append(')').toString();
			}
			b.append(", ");
		}
	}

	private String schemaString (final Collection<String> colums) {

		final int iMax = colums.size() - 1;
		if (iMax == -1) {
			return "()";
		}

		final StringBuilder b = new StringBuilder();
		b.append('(');
		for (int i = 0;; i++) {
			b.append(colums.getElementAt(i));
			if (i == iMax) {
				return b.append(')').toString();
			}
			b.append(", ");
		}
	}

}
