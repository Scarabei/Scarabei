
package com.jfixby.cmns.db.api;

public interface DBComponent {

	DBConfig newDBConfig ();

	DataBase newDB (DBConfig config);

}
