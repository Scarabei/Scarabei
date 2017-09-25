
package com.jfixby.scarabei.api.java;

public interface ByteArray {

	byte[] toArray ();

	long size ();

	int getByte (int i);

	void append (ByteArray bytes);

}
