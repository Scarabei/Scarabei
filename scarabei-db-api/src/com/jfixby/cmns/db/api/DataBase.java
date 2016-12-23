
package com.jfixby.cmns.db.api;

import java.io.IOException;

public interface DataBase {

	Table getTable (String tableName) throws IOException;

}
