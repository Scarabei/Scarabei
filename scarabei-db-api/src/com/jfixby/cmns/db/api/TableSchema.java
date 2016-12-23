
package com.jfixby.cmns.db.api;

import com.jfixby.cmns.api.collections.Collection;

public interface TableSchema {

	int indexOf (String key);

	Collection<String> getColumns ();

}
