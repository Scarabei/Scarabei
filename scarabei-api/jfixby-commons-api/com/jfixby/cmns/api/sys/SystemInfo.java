
package com.jfixby.cmns.api.sys;

import com.jfixby.cmns.api.collections.Map;

public interface SystemInfo {

	Map<String, String> listParameters ();

	void putValue (String key, Object value);

	void print ();

}
