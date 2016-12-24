
package com.jfixby.scarabei.red.io;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.io.JavaBitInputStream;
import com.jfixby.scarabei.api.io.JavaBitStreamMode;
import com.jfixby.scarabei.api.util.EditableBinaryCode;
import com.jfixby.scarabei.api.util.JUtils;

public class RedJavaBitInputStream implements JavaBitInputStream {

	final private InputStream is;
	private int frameSize = 8;
	private final EditableBinaryCode buffer;
	private int read_byte;
	private byte[] buff = new byte[1];
	private JavaBitStreamMode mode;

	public RedJavaBitInputStream (java.io.InputStream is, JavaBitStreamMode mode) {
		this.mode = Debug.checkNull("JavaBitStreamMode", mode);
		this.is = is;
		buffer = JUtils.newBinaryCode();
	}

	@Override
	public void setFrameSize (int frameSize) {
		this.frameSize = frameSize;
	}

	@Override
	public int read () throws IOException {
		if (mode == JavaBitStreamMode.COMPRESSED_BITS) {
			if (buffer.size() < 8) {
				// is.read(buff, 0, 1);
				// this.read_byte = buff[0];
				this.read_byte = is.read();
				// L.d("READ BYTE", Integer.toBinaryString(read_byte));
				buffer.append(read_byte, 8);
			}
			return buffer.retrieveBits(frameSize);
		} else {
			return is.read();
		}
	}

}
