
package com.jfixby.scarabei.api.log;

public interface LoggerComponent {

	void d (Object msg);

	void d (Object... msg);

	void d (Object tag, Object msg);

	void e (Object msg);

	void e (Throwable msg);

	void e (Object tag, Object msg);

	void e (Object tag, Throwable err);

	void d ();

	void e ();

	void d_appendChars (Object msg);

	public String toString (Object[] array);

	String stackTraceToString (Throwable e);

// String throwableToString (Throwable aThrowable);

}
