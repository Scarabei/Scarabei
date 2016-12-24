
package com.jfixby.scarabei.db.api;

public interface DBComponent {

	DBConfig newDBConfig ();

	DataBase newDB (DBConfig config);

}
