
package com.jfixby.cmns.api.sys;

import com.jfixby.cmns.api.collections.Mapping;

public interface SystemInfo {

	Mapping<String, String> listParameters ();

	void putValue (String key, Object value);

	void print ();

}
