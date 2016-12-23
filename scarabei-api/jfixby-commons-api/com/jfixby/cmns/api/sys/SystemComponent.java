
package com.jfixby.cmns.api.sys;

import com.jfixby.cmns.api.time.TimeStream;

public interface SystemComponent {

	TimeStream SystemTime ();

	TimeStream NoTime ();

	void exit ();

	boolean sleep (long period);

	boolean isWindows ();

	boolean isUnix ();

	boolean isMac ();

	SystemInfo getSystemInfo ();

	void yeld ();

	void wait (Object lock);

}
