
package com.jfixby.cmns.db.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;

public class MySQLTableSchema {

	private final MySQLTable mySQLTable;
	private final List<String> columns = Collections.newList();
	private boolean loaded = false;

	public MySQLTableSchema (final MySQLTable mySQLTable) {
		this.mySQLTable = mySQLTable;
	}

	void load () throws IOException {
		// --- LISTING DATABASE COLUMN NAMES ---
		final MySQLConnection connection = this.mySQLTable.db.obtainConnection();
		connection.checkIsOpen();
		try {
			final Connection mysql_connection = connection.getConnection();
			final DatabaseMetaData meta = mysql_connection.getMetaData();
			final ResultSet resultSet = meta.getColumns(this.mySQLTable.db.getDBName(), null, this.mySQLTable.sql_table_name, "%");
			while (resultSet.next()) {
				this.columns.add(resultSet.getString(4));
// log.info("Column Name of table " + tableName + " = " + );
			}
			this.loaded = true;

		} catch (final SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.mySQLTable.db.releaseConnection(connection);
		}
	}

	public Collection<String> getColumns () throws IOException {
		this.loadIfNotLoaded();
		return this.columns;
	}

	public MySQLTableSchema loadIfNotLoaded () throws IOException {
		if (this.loaded) {
			return this;
		}
		this.load();
		return this;
	}

	public void print () {
		this.columns.print("schema");
	}

	public int indexOf (final String key) {
		final int result = this.columns.indexOf(key);
		if (result == -1) {
			this.print();
		}
		return result;
	}

}
