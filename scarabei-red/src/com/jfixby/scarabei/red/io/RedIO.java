
package com.jfixby.scarabei.red.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.file.FileOutputStream;
import com.jfixby.scarabei.api.io.Buffer;
import com.jfixby.scarabei.api.io.BufferInputStream;
import com.jfixby.scarabei.api.io.BufferOutputStream;
import com.jfixby.scarabei.api.io.Data;
import com.jfixby.scarabei.api.io.GZipInputStream;
import com.jfixby.scarabei.api.io.GZipOutputStream;
import com.jfixby.scarabei.api.io.IOComponent;
import com.jfixby.scarabei.api.io.InputStream;
import com.jfixby.scarabei.api.io.InputStreamOpener;
import com.jfixby.scarabei.api.io.JavaBitInputStream;
import com.jfixby.scarabei.api.io.JavaBitOutputStream;
import com.jfixby.scarabei.api.io.JavaBitStreamMode;
import com.jfixby.scarabei.api.io.LazyInputStream;
import com.jfixby.scarabei.api.io.OutputStream;
import com.jfixby.scarabei.api.io.OutputStreamOpener;
import com.jfixby.scarabei.api.io.StreamPipe;
import com.jfixby.scarabei.api.io.U_StreamPipeProgressListener;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.util.JUtils;

public class RedIO implements IOComponent {

	@Override
	public StreamPipe newStreamPipe (final InputStream input_stream, final OutputStream output_stream,
		final U_StreamPipeProgressListener progress_listener) {
		return new RedStreamPipe(input_stream, output_stream, progress_listener);
	}

	@Override
	public Buffer newBuffer () {
		return new RedBuffer();
	}

	@Override
	public BufferOutputStream newBufferOutputStream () {
		return new RedBufferOutputStream();
	}

	@Override
	public Buffer readStreamToBuffer (final InputStream input_stream) throws IOException {
		final ByteArray bytes = input_stream.readAll();

		return new RedBuffer(bytes);
	}

	@Override
	public Buffer newBuffer (final ByteArray bytes) {
		return new RedBuffer(bytes);
	}

	@Override
	public BufferInputStream newBufferInputStream (final Buffer buffer) {
		return new RedBufferInputStream(buffer);
	}

	@Override
	public void writeBufferToStream (final Buffer buffer, final FileOutputStream os) throws IOException {
		final BufferInputStream is = this.newBufferInputStream(buffer);
		final StreamPipe pipe = this.newStreamPipe(is, os, null);
		pipe.transferAll();
	}

	@Override
	public InputStream newInputStream (final InputStreamOpener opener) {
		final AbstractRedInputStream<RedJavaInputStreamOperator> stream = new AbstractRedInputStream<RedJavaInputStreamOperator>(
			new RedJavaInputStreamOperator(opener));
// stream.open();
		return stream;
	}

	@Override
	public OutputStream newOutputStream (final OutputStreamOpener opener) {
		final AbstractRedOutputStream<RedJavaOutputStreamOperator> stream = new AbstractRedOutputStream<RedJavaOutputStreamOperator>(
			new RedJavaOutputStreamOperator(opener));
// stream.open();
		return stream;
	}

// @Override
// public OutputStream toOutputStream (final java.io.OutputStream java_output_stream) throws IOException {
//
// final AbstractRedOutputStream<RedJavaOutputStreamOperator> stream = new AbstractRedOutputStream<RedJavaOutputStreamOperator>(
// new RedJavaOutputStreamOperator(java_output_stream));
// stream.open();
// return stream;
// }

	@Override
	public int readByte (final java.io.InputStream javaInputStream) throws IOException {
		return javaInputStream.read();
	}

	@Override
	final public int readInt (final java.io.InputStream javaInputStream) throws IOException {
		final int b0 = javaInputStream.read() << (8 * 3);
		final int b1 = javaInputStream.read() << (8 * 2);
		final int b2 = javaInputStream.read() << (8 * 1);
		final int b3 = javaInputStream.read() << (8 * 0);
		final int result = b0 | b1 | b2 | b3;
		// L.d(Integer.toBinaryString(b0));
		// L.d(Integer.toBinaryString(b1));
		// L.d(Integer.toBinaryString(b2));
		// L.d(Integer.toBinaryString(b3));
		// L.d(Integer.toBinaryString(result));
		return result;//
	}

	@Override
	final public int readShort (final java.io.InputStream javaInputStream) throws IOException {
		final int b3 = javaInputStream.read() << (8 * 0);
		final int b2 = javaInputStream.read() << (8 * 1);

		return (b2 | b3);//
	}

	@Override
	public void writeInt (final java.io.OutputStream javaOutputStream, final int value) throws IOException {
		javaOutputStream.write((value >> 8 * 3) & 0xff);
		javaOutputStream.write((value >> 8 * 2) & 0xff);
		javaOutputStream.write((value >> 8 * 1) & 0xff);
		javaOutputStream.write((value >> 8 * 0) & 0xff);
	}

	@Override
	public void writeByte (final java.io.OutputStream javaOutputStream, final int value) throws IOException {
		javaOutputStream.write(value);
	}

	@Override
	public GZipOutputStream newGZipStream (final OutputStream os) throws IOException {
		return new RedGZipOutputStream(os);
	}

	@Override
	public GZipInputStream newGZipStream (final InputStream is) throws IOException {
		return new RedGZipInputStream(is);
	}

	@Override
	final public void writeBytes (final java.io.OutputStream javaOutputStream, final int[] bytes) throws IOException {
		for (int i = 0; i < bytes.length; i++) {
			javaOutputStream.write(bytes[i]);
		}

	}

	@Override
	public void readBytes (final java.io.InputStream javaInputStream, final int[] array) throws IOException {
		for (int i = 0; i < array.length; i++) {
			array[i] = javaInputStream.read();
		}
	}

	@Override
	public JavaBitInputStream newBitInputStream (final java.io.InputStream is) {
		return new RedJavaBitInputStream(is, JavaBitStreamMode.SIMPLE_BYTE);
	}

	@Override
	public JavaBitInputStream newBitInputStream (final java.io.InputStream is, final JavaBitStreamMode mode) {
		return new RedJavaBitInputStream(is, mode);
	}

	@Override
	public JavaBitOutputStream newBitOutputStream (final java.io.OutputStream os, final JavaBitStreamMode mode) {
		return new RedJavaBitOutputStream(os, mode);
	}

	@Override
	public JavaBitOutputStream newBitOutputStream (final java.io.OutputStream os) {
		return new RedJavaBitOutputStream(os, JavaBitStreamMode.SIMPLE_BYTE);
	}

	@Override
	public void writeShort (final java.io.OutputStream javaOutputStream, final int value) throws IOException {
		javaOutputStream.write((value >> 8 * 0) & 0xff);
		javaOutputStream.write((value >> 8 * 1) & 0xff);

	}

	@Override
	public ByteArray serialize (final Serializable object) throws IOException {
		final ByteArrayOutputStream buff = new ByteArrayOutputStream();
		final ObjectOutputStream os = new ObjectOutputStream(buff);
		os.writeObject(object);
		os.flush();
// os.close();
		buff.close();
		return JUtils.newByteArray(buff.toByteArray());
	}

	@Override
	public void serialize (final Serializable object, final OutputStream output_stream) throws IOException {
		final java.io.OutputStream jos = output_stream.toJavaOutputStream();
		final ObjectOutputStream os = new ObjectOutputStream(jos);
		os.writeObject(object);
		os.flush();
		jos.flush();
	}

	@Override
	public <T> T deserialize (final Class<T> type, final InputStream input_stream) throws IOException {
		Debug.checkNull("input_stream", input_stream);
		Debug.checkNull("type", type);
		final java.io.InputStream jis = input_stream.toJavaInputStream();
		final ObjectInputStream os = new ObjectInputStream(jis);
		try {
			final T object = (T)os.readObject();
			return object;
		} catch (final Throwable e) {
			throw new IOException(e);
		} finally {
			// forceClose(jis);
			// forceClose(os);
		}
	}

	@Override
	public void forceClose (final java.io.OutputStream os) {
		if (os == null) {
			return;
		}
		try {
			os.flush();
		} catch (final IOException ignored) {
		}
		try {
			os.close();
		} catch (final IOException ignored) {
		}
	}

	@Override
	public void forceClose (final java.io.InputStream is) {
		if (is == null) {
			return;
		}
		try {
			is.close();
		} catch (final IOException ignored) {
		}
	}

	@Override
	public LazyInputStream newLazyInputStream (final InputStream input_stream) {
		return new RedLazyInputStream(input_stream);
	}

	@Override
	public void forceClose (final OutputStream os) {
		if (os != null) {
			if (os.isOpen()) {
				os.close();
			}
		}
	}

	@Override
	public void forceClose (final InputStream is) {
		if (is != null) {
			if (is.isOpen()) {
				is.close();
			}
		}
	}

	@Override
	public ByteArray compress (final ByteArray data) {
		Debug.checkNull("data", data);
		try {
			final BufferOutputStream os = this.newBufferOutputStream();
			os.open();
			{
				final GZipOutputStream gzip = this.newGZipStream(os);
				gzip.open();

				gzip.write(data);
				gzip.flush();
				gzip.close();
			}
			os.flush();
			os.close();
			return os.getBytes();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ByteArray decompress (final ByteArray data) {
		Debug.checkNull("data", data);
		try {
			final Buffer buffer = this.newBuffer(data);
			ByteArray result = null;
			final BufferInputStream os = this.newBufferInputStream(buffer);
			os.open();
			{
				final GZipInputStream gzip = this.newGZipStream(os);
				gzip.open();
				result = gzip.readAll();
				gzip.close();
			}

			os.close();
			return result;
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public ByteArray readMax (final InputStream is, final long maxBytesToRead) throws IOException {
		final ByteArrayOutputStream bis = new ByteArrayOutputStream();
		long read = 0;
		while (true) {
			final Data value = is.read();
			if (value.isEndOfStream()) {
				break;
			}
			bis.write(value.toInt());
			read++;
			if (read >= maxBytesToRead) {
				break;
			}
		}
		bis.close();
		final byte[] bytes = bis.toByteArray();
		return JUtils.newByteArray(bytes);
	}

	@Override
	public <T> T deserialize (final Class<T> type, final ByteArray bytes) throws IOException {
		return this.deserialize(type, bytes.toArray());
	}

	@Override
	public <T> T deserialize (final Class<T> type, final byte[] bytes) throws IOException {
		Debug.checkNull("bytes", bytes);
		Debug.checkNull("type", type);
		final ByteArrayInputStream jis = new ByteArrayInputStream(bytes);
		final ObjectInputStream os = new ObjectInputStream(jis);
		try {
			final T object = (T)os.readObject();
			return object;
		} catch (final Throwable e) {
			e.printStackTrace();
			throw new IOException(e);
		} finally {
			this.forceClose(jis);
			this.forceClose(os);
		}
	}

}
