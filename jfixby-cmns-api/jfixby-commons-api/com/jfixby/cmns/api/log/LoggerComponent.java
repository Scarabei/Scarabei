
package com.jfixby.cmns.api.log;

public interface LoggerComponent {

	void d (Object msg);

	void d (Object... msg);

	void d (Object tag, Object msg);

	void e (Object msg);

	void e (Object tag, Object msg);

	void d ();

	void e ();

	void d_addChars (Object msg);

	public String toString (Object[] array);

	String stackTraceToString (Throwable e);

// String throwableToString (Throwable aThrowable);

}
