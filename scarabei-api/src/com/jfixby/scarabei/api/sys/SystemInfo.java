
package com.jfixby.scarabei.api.sys;

import com.jfixby.scarabei.api.collections.Map;

public interface SystemInfo {

	Map<String, String> listParameters ();

	void putValue (String key, Object value);

	void print ();

}
