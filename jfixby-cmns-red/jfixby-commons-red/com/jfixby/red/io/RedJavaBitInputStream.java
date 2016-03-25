package com.jfixby.red.io;

import java.io.IOException;
import java.io.InputStream;

import com.jfixby.cmns.api.io.JavaBitInputStream;
import com.jfixby.cmns.api.util.EditableBinaryCode;
import com.jfixby.cmns.api.util.JUtils;

public class RedJavaBitInputStream implements JavaBitInputStream {

    final private InputStream is;
    private int frameSize = 8;
    private final EditableBinaryCode buffer;
    private int read_byte;
    private byte[] buff = new byte[1];

    public RedJavaBitInputStream(java.io.InputStream is) {
	this.is = is;
	buffer = JUtils.newBinaryCode();
    }

    @Override
    public void setFrameSize(int frameSize) {
	this.frameSize = frameSize;
    }

    @Override
    public int read() throws IOException {
	if (buffer.size() < 8) {
	    // is.read(buff, 0, 1);
	    // this.read_byte = buff[0];
	    this.read_byte = is.read();
	    // L.d("READ BYTE", Integer.toBinaryString(read_byte));
	    buffer.append(read_byte, 8);
	}
	return buffer.retrieveBits(frameSize);
    }

}
