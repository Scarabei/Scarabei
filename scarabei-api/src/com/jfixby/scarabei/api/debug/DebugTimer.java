
package com.jfixby.scarabei.api.debug;

public interface DebugTimer {

	void reset ();

	double getTime ();// in seconds

	void printTime (String tag);

	void printTimeAbove (double threshold_in_seconds, String tag);

	void printTimeAbove (long threshold_in_milliseconds, String tag);

	DEBUG_TIMER_MODE getMode ();

}
