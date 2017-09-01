
package com.jfixby.scarabei.api.db;

public interface Entry {

	String getValue (String parameterName);

	void set (TableSchema schema, int indexOf, Object value);

	Table getOwner ();

}
