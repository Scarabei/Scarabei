
package com.jfixby.scarabei.api.util;

public interface EditableBinaryCode extends BinaryCode {
	void append (BinaryCode bitform);

	EditableBinaryCode append (int bits, int numberOfBits);

	void append (boolean bit);

	void insertAt (BinaryCode bitform, int place);

	int retrieveByte ();

	int retrieveBits (int numberOfBits);
}
