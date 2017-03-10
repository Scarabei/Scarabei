
package com.jfixby.scarabei.api.util;

public interface BinaryCode {

	public static final boolean ZERO = false;
	public static final boolean ONE = !ZERO;

	int size ();

	boolean getBit (int i);

	EditableBinaryCode copy ();

}
