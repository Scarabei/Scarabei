
package com.jfixby.scarabei.db.api;

public interface Entry {

	String getValue (String parameterName);

	void set (TableSchema schema, int indexOf, Object value);

}
