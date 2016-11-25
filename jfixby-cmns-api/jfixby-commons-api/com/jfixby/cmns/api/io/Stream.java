
package com.jfixby.cmns.api.io;

public interface Stream {

	void close ();

	public STREAM_STATE getState ();

	void open ();

	public boolean isOpen ();

	public boolean isClosed ();
}
