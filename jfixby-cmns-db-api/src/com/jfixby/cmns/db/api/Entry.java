
package com.jfixby.cmns.db.api;

public interface Entry {

	String getValue (String parameterName);

	void set (TableSchema schema, int indexOf, Object value);

}
