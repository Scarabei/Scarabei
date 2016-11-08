
package com.jfixby.cmns.api.io;

public interface Stream {

	void close ();

	public STREAM_STATE getState ();

	void open ();

	public void setPedanticMode (boolean pedanticMode);

	public boolean isInPedanticMode ();

	public boolean isOpen ();

	public boolean isClosed ();
}
