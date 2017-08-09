
package com.jfixby.scarabei.api.debug;

import com.jfixby.scarabei.api.collections.Collection;

public interface DebugComponent {

	void exit (boolean condition);

	<T> T checkNull (String parameter_name, T value);

	<T> T checkNull (T value);

	String checkEmpty (String parameter_name, String value);

	void checkTrue (boolean flag);

	void checkTrue (String flag_name, boolean flag);

	DebugTimer newTimer ();

	DebugTimer newTimer (DEBUG_TIMER_MODE mode);

	void reportWarning (String msg);

	void checkCurrentThreadIsMain ();

	Collection<StackTraceElement> getStackTrace ();

}
