package com.jfixby.red.io;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.io.JavaBitOutputStream;
import com.jfixby.cmns.api.math.IntegerMath;
import com.jfixby.cmns.api.util.BitForm;
import com.jfixby.cmns.api.util.JUtils;

public class RedJavaBitOutputStream implements JavaBitOutputStream {

    final private OutputStream os;
    private int frameSize;
    private long valueLimit;
    final BitForm buffer = JUtils.newBitForm();
    int max_buffer_size = 512 * 8;

    public RedJavaBitOutputStream(java.io.OutputStream os) throws IOException {
	this.os = os;
	this.setFrameSize(8);
    }

    @Override
    public void setFrameSize(final int frameSize) throws IOException {
	Debug.checkTrue("frame size = " + frameSize, frameSize > 0);
	this.frameSize = frameSize;
	this.valueLimit = IntegerMath.component().powerOfTwo(frameSize);
    }

    @Override
    public void write(final int bits) throws IOException {
	if (bits >= this.valueLimit) {
	    BigInteger tmp = new BigInteger("" + bits);
	    String bits_strign = tmp.toString(2);
	    throw new IOException("Frame overflow: " + this.frameSize + " by " + bits_strign);
	}
	writeBits(bits, this.frameSize);
    }

    public void writeBits(int bits, int numberOfBitsToWrite) throws IOException {
	final BitForm bitform = JUtils.component().bitformOf(bits, numberOfBitsToWrite);
	buffer.append(bitform);
	if (buffer.size() >= max_buffer_size) {
	    flushWhatCanBeFlushed();
	}

    }

    private void flushWhatCanBeFlushed() throws IOException {
	while (this.buffer.size() >= 8) {
	    int byteValue = this.buffer.retrieveByte();
	    os.write(byteValue);
	}
    }

    public int getMissingTailSize() {
	return 8 - this.buffer.size() % 8;
    }

    @Override
    public void finalizeStream() throws IOException {
	final int missimg_bits = getMissingTailSize();
	writeBits(0, missimg_bits);
	flushWhatCanBeFlushed();
	if (this.buffer.size() > 0) {
	    throw new IOException("Failed to finalizeStream: " + this.buffer);
	}
    }

}
