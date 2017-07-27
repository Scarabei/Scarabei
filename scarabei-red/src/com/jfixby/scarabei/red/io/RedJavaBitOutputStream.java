
package com.jfixby.scarabei.red.io;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.io.JavaBitOutputStream;
import com.jfixby.scarabei.api.io.JavaBitStreamMode;
import com.jfixby.scarabei.api.math.IntegerMath;
import com.jfixby.scarabei.api.util.BinaryCode;
import com.jfixby.scarabei.api.util.EditableBinaryCode;
import com.jfixby.scarabei.api.util.JUtils;

public class RedJavaBitOutputStream implements JavaBitOutputStream {

	final private OutputStream os;
	private int frameSize;
	private long valueLimit;
	final EditableBinaryCode buffer = JUtils.newBinaryCode();
	int max_buffer_size = 8;
	private int byteValue;
	private JavaBitStreamMode mode;

	public RedJavaBitOutputStream (java.io.OutputStream os, JavaBitStreamMode mode) {
		this.os = os;
		this.mode = Debug.checkNull("JavaBitStreamMode", mode);
		this.setFrameSize(8);
	}

	@Override
	public void setFrameSize (final int frameSize) {
		Debug.checkTrue("frame size = " + frameSize, frameSize > 0);
		this.frameSize = frameSize;
		this.valueLimit = IntegerMath.component().powerOfTwo(frameSize);
	}

	@Override
	public void write (final int bits) throws IOException {
		if (mode == JavaBitStreamMode.COMPRESSED_BITS) {

			if (bits >= this.valueLimit) {
				BigInteger tmp = new BigInteger("" + bits);
				String bits_strign = tmp.toString(2);
				throw new IOException("Frame overflow: " + this.frameSize + " by " + bits_strign);
			}
			writeBits(bits, this.frameSize);
		} else {
			os.write(bits);
		}
	}

	public void writeBits (int bits, int numberOfBitsToWrite) throws IOException {
		final BinaryCode bitform = JUtils.component().binaryCodeOf(bits, numberOfBitsToWrite);
		buffer.append(bitform);
		if (buffer.size() >= max_buffer_size) {
			flushWhatCanBeFlushed();
		}

	}

	private void flushWhatCanBeFlushed () throws IOException {
		while (this.buffer.size() >= 8) {
			byteValue = this.buffer.retrieveByte();
			// byteValue = Integer.parseInt("10101010", 2);
			// L.d("WRITE BYTE", Integer.toBinaryString(byteValue));
			os.write(byteValue);
		}
	}

	public int getMissingTailSize () {
		if (this.buffer.size() == 0) {
			return 0;
		}
		return 8 - this.buffer.size() % 8;
	}

	@Override
	public void finalizeStream () throws IOException {
		final int missimg_bits = getMissingTailSize();
		if (missimg_bits != 0) {
			writeBits(0, missimg_bits);
		}
		flushWhatCanBeFlushed();
		if (this.buffer.size() > 0) {
			throw new IOException("Failed to finalizeStream: " + this.buffer);
		}
	}

}
