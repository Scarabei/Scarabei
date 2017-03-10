
package com.jfixby.scarabei.api.time;

public interface TimeStream {

	public static final long SECOND = 1000L;
	public static final long MINUTE = SECOND * 60L;
	public static final long HOUR = MINUTE * 60L;
	public static final long DAY = HOUR * 24L;
	public static final long WEEK = DAY * 7L;

	public long currentTimeMillis ();

}
