
package com.jfixby.scarabei.red.db.stupid;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.db.DataBase;
import com.jfixby.scarabei.api.db.Table;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.names.ID;

public class StupidDB implements DataBase {

	private final ID dbName;
	final Map<String, StupidTable> tables = Collections.newMap();
	final File storageFolder;
	private final File dbFile;

	public StupidDB (final StupidDBConfig config) throws IOException {
		this.dbName = config.dbName;
		Debug.checkNull("dbName", this.dbName);
		this.storageFolder = config.storageFolder;
		Debug.checkNull("storageFolder", this.storageFolder);
		this.dbFile = this.storageFolder.child(this.dbName.child("json").toString());
		if (this.dbFile.exists()) {
			this.readStorage();
		} else {
			this.writeStorage();
		}
	}

	private void readStorage () throws IOException {
		final String string = this.dbFile.readToString();
		L.d("reading", this.dbFile);
		final SrlzdDBSchema schema = Json.deserializeFromString(SrlzdDBSchema.class, string);
		for (final String tableName : schema.tables) {
			final StupidTable table = new StupidTable(this, tableName);
			this.tables.put(tableName, table);
			L.d("   table loaded", table);
		}
	}

	private void writeStorage () throws IOException {
		final SrlzdDBSchema schema = new SrlzdDBSchema();
		schema.tables.addAll(this.tables.keys().toJavaList());
		final String data = Json.serializeToString(schema).toString();
		L.d("writing", this.dbFile);
		this.dbFile.writeString(data);
	}

	@Override
	public Table getTable (final String tableName) throws IOException {
		Debug.checkNull("tableName", tableName);
		this.storageFolder.makeFolder();

		final StupidTable table = this.tables.get(tableName);
		if (table == null) {
			throw new IOException("Table<" + tableName + "> not found");
		}
		return table;
	}

	@Override
	public ID getDBName () {
		return this.dbName;
	}

}
