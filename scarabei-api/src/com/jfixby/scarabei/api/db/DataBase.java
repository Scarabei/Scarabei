
package com.jfixby.scarabei.api.db;

import java.io.IOException;

public interface DataBase {

	Table getTable (String tableName) throws IOException;

}
