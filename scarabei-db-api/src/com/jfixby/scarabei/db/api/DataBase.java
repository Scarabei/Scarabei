
package com.jfixby.scarabei.db.api;

import java.io.IOException;

public interface DataBase {

	Table getTable (String tableName) throws IOException;

}
