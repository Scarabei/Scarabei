
package com.jfixby.scarabei.red.db.simple;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.db.DataBase;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.db.TableSchema;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.red.db.simple.srlzd.SrlzdDBSchema;
import com.jfixby.scarabei.red.db.simple.srlzd.SrlzdTableSchema;

public class SimpleDB implements DataBase {

	private final ID dbName;
	final Map<String, SimpleTable> tables = Collections.newMap();
	final File storageFolder;
	private final File dbFile;

	public SimpleDB (final SimpleDBConfig config) throws IOException {
		this.dbName = config.dbName;
		Debug.checkNull("dbName", this.dbName);
		this.storageFolder = config.storageFolder;
		Debug.checkNull("storageFolder", this.storageFolder);
		this.dbFile = this.storageFolder.child(this.dbName.child("json").toString());
		if (config.readFromStorage) {
			this.readStorage();
		} else {
			this.writeStorage();
		}
	}

	private void readStorage () throws IOException {
		final String string = this.dbFile.readToString();
		L.d("reading", this.dbFile);
		final SrlzdDBSchema schema = Json.deserializeFromString(SrlzdDBSchema.class, string);
		for (final String tableName : schema.tables.keySet()) {
			final SrlzdTableSchema sztSchema = schema.tables.get(tableName);
			final SimpleTableSchema tSchema = this.deserialize(sztSchema);
			final SimpleTable table = new SimpleTable(this, tableName, tSchema);
			this.tables.put(tableName, table);
			L.d("   table loaded", table);
		}
	}

	private SimpleTableSchema deserialize (final SrlzdTableSchema sztSchema) throws IOException {
		final SimpleTableSchema s = new SimpleTableSchema();
		s.addAll(sztSchema.columns);
		return s;
	}

	public SimpleTable newTable (final SimpleTableSpecs specs) throws IOException {
		final String tableName = specs.tableName;
		Debug.checkNull("tableName", tableName);
		Debug.checkEmpty("tableName", tableName);
		final SimpleTableSchema tSchema = new SimpleTableSchema();
		tSchema.addAll(specs.columns);
		final SimpleTable table = new SimpleTable(this, tableName, tSchema);
		this.tables.put(tableName, table);
		L.d("   table created", table);
		this.writeStorage();
		return table;
	}

	private void writeStorage () throws IOException {
		final SrlzdDBSchema schema = new SrlzdDBSchema();
		for (final String key : this.tables.keys()) {
			final SimpleTable val = this.tables.get(key);
			final SrlzdTableSchema srlzdSchema = this.serialize(val.getSchema());
			schema.tables.put(key, srlzdSchema);

		}

		final String data = Json.serializeToString(schema).toString();
		L.d("writing", this.dbFile);
		this.dbFile.writeString(data);
	}

	private SrlzdTableSchema serialize (final TableSchema tableSchema) {
		final SrlzdTableSchema s = new SrlzdTableSchema();
		for (final String k : tableSchema.getColumns()) {
			s.columns.add(k);
		}
		return s;
	}

	@Override
	public Table getTable (final String tableName) throws IOException {
		Debug.checkNull("tableName", tableName);
		this.storageFolder.makeFolder();

		final SimpleTable table = this.tables.get(tableName);
		if (table == null) {
			throw new IOException("Table<" + tableName + "> not found");
		}
		return table;
	}

	@Override
	public ID getDBName () {
		return this.dbName;
	}

	public void drop () throws IOException {
		for (final SimpleTable t : this.tables.values()) {
			t.drop();
		}
		this.tables.clear();
		this.writeStorage();
	}

	public SimpleTableSpecs newTableSpecs () {
		return new SimpleTableSpecs();
	}

}
