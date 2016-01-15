package com.jfixby.cmns.api.log;

public interface LoggerComponent {

	void d(Object msg);

	void d(String tag, Object msg);

	void e(Object msg);

	void e(String tag, Object msg);

	void d();

	void e();

	void d_addChars(Object msg);

	public String toString(Object[] array);

}
