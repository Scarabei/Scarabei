
package com.jfixby.scarabei.api.sys;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Map;

public interface SystemInfo {

	Map<ID, String> listParameters ();

	void putValue (ID key, Object value);

}
