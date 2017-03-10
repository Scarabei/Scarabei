
package com.jfixby.scarabei.db.api;

import com.jfixby.scarabei.api.collections.Collection;

public interface TableSchema {

	int indexOf (String key);

	Collection<String> getColumns ();

}
