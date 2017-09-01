
package com.jfixby.scarabei.api.db;

public interface Entry {

	Object getValue (String key);

	void setValue (String key, Object value);

	Table getOwner ();

}
