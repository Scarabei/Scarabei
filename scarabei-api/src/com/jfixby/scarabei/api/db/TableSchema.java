
package com.jfixby.scarabei.api.db;

import com.jfixby.scarabei.api.collections.Collection;

public interface TableSchema {

	int indexOf (String key);

	Collection<String> getColumns ();

}
