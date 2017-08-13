
package com.jfixby.scarabei.api.sys;

import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.time.TimeStream;

public interface SystemComponent {

	TimeStream SystemTime ();

	TimeStream NoTime ();

	void exit ();

	boolean sleep (long period);

	boolean isWindows ();

	boolean isUnix ();

	boolean isMac ();

	Map<ID, String> getSystemInfo ();

	void yeld ();

	void wait (Object lock);

	void addOnExitListener (OnExitListener listener);

	boolean isIOS ();

}
