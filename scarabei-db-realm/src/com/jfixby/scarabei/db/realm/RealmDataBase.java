
package com.jfixby.scarabei.db.realm;

import java.io.IOException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.db.DataBase;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.names.ID;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmSchema;

class RealmDataBase implements DataBase {
	final Map<String, RealmTable> tables = Collections.newMap();
	private final Realm realm;
	private final String dbName;
	private final RealmSchema dbSchema;

	RealmDataBase (final RealmConfig config) {
		this.dbName = config.dbName;
		Debug.checkNull("dbName", this.dbName);
		Debug.checkEmpty("dbName", this.dbName);

		final RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
		Realm.deleteRealm(realmConfiguration);
		this.realm = Realm.getInstance(realmConfiguration);
		this.dbSchema = this.realm.getSchema();

	}

	@Override
	public String getDBName () {
		return this.dbName;
	}

	@Override
	public RealmTable getTable (final String name) throws IOException {
		Debug.checkNull("name", name);
		Debug.checkEmpty("name", name);
		RealmTable table = this.tables.get(name);
		if (table == null) {
			table = new RealmTable(this, name);
			this.tables.put(name, table);
		}
		return table;
	}

	public Realm obtainConnection () {
		return this.realm;
	}

	public <C> Class<C> classforName (final ID className) throws ClassNotFoundException {
		final ClassLoader classLoader = this.getClass().getClassLoader();
		final Class<C> klass = (Class<C>)Class.forName(className.toString(), true, classLoader);
		return klass;
	}

}
