
package com.jfixby.scarabei.api.db;

import java.io.IOException;

import com.jfixby.scarabei.api.names.ID;

public interface DataBase {

	Table getTable (ID tableName) throws IOException;

	public ID getDBName ();

}
