package com.jfixby.red.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.io.BufferInputStream;
import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.ForceCloseable;
import com.jfixby.cmns.api.io.GZipInputStream;
import com.jfixby.cmns.api.io.GZipOutputStream;
import com.jfixby.cmns.api.io.IOComponent;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.io.JavaBitInputStream;
import com.jfixby.cmns.api.io.JavaBitOutputStream;
import com.jfixby.cmns.api.io.JavaBitStreamMode;
import com.jfixby.cmns.api.io.OutputStream;
import com.jfixby.cmns.api.io.StreamPipe;
import com.jfixby.cmns.api.io.U_StreamPipeProgressListener;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.JUtils;

public class RedIO implements IOComponent {

    @Override
    public StreamPipe newStreamPipe(InputStream input_stream, OutputStream output_stream,
	    U_StreamPipeProgressListener progress_listener) {
	return new RedStreamPipe(input_stream, output_stream, progress_listener);
    }

    @Override
    public Buffer newBuffer() {
	return new RedBuffer();
    }

    @Override
    public BufferOutputStream newBufferOutputStream() {
	return new RedBufferOutputStream();
    }

    @Override
    public Buffer readStreamToBuffer(InputStream input_stream) throws IOException {
	ByteArray bytes = input_stream.readAll();

	return new RedBuffer(bytes);
    }

    @Override
    public Buffer newBuffer(ByteArray bytes) {
	return new RedBuffer(bytes);
    }

    @Override
    public BufferInputStream newBufferInputStream(Buffer buffer) {
	return new RedBufferInputStream(buffer);
    }

    @Override
    public void writeBufferToStream(Buffer buffer, FileOutputStream os) throws IOException {
	BufferInputStream is = this.newBufferInputStream(buffer);
	StreamPipe pipe = this.newStreamPipe(is, os, null);
	pipe.transferAll();
    }

    @Override
    public InputStream toInputStream(java.io.InputStream java_input_stream) throws IOException {
	return new AbstractRedInputStream(java_input_stream);
    }

    @Override
    public OutputStream toOutputStream(java.io.OutputStream java_output_stream) throws IOException {
	return new AbstractRedOutputStream(java_output_stream);
    }

    @Override
    public int readByte(java.io.InputStream javaInputStream) throws IOException {
	return javaInputStream.read();
    }

    @Override
    final public int readInt(final java.io.InputStream javaInputStream) throws IOException {
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
    final public int readShort(final java.io.InputStream javaInputStream) throws IOException {
	final int b3 = javaInputStream.read() << (8 * 0);
	final int b2 = javaInputStream.read() << (8 * 1);

	return (b2 | b3);//
    }

    @Override
    public void writeInt(java.io.OutputStream javaOutputStream, int value) throws IOException {
	javaOutputStream.write((value >> 8 * 3) & 0xff);
	javaOutputStream.write((value >> 8 * 2) & 0xff);
	javaOutputStream.write((value >> 8 * 1) & 0xff);
	javaOutputStream.write((value >> 8 * 0) & 0xff);
    }

    @Override
    public void writeByte(java.io.OutputStream javaOutputStream, int value) throws IOException {
	javaOutputStream.write(value);
    }

    @Override
    public void forceClose(ForceCloseable os) {
	if (os != null) {
	    os.forceClose();
	}
    }

    @Override
    public GZipOutputStream newGZipStream(OutputStream os) throws IOException {
	return new RedGZipOutputStream(os);
    }

    @Override
    public GZipInputStream newGZipStream(InputStream is) throws IOException {
	return new RedGZipInputStream(is);
    }

    @Override
    final public void writeBytes(final java.io.OutputStream javaOutputStream, final int[] bytes) throws IOException {
	for (int i = 0; i < bytes.length; i++) {
	    javaOutputStream.write(bytes[i]);
	}

    }

    @Override
    public void forceClose(Closeable os) {
	try {
	    os.close();
	} catch (IOException ignored) {
	}
    }

    @Override
    public void readBytes(java.io.InputStream javaInputStream, int[] array) throws IOException {
	for (int i = 0; i < array.length; i++) {
	    array[i] = javaInputStream.read();
	}
    }

    @Override
    public JavaBitInputStream newBitInputStream(java.io.InputStream is) {
	return new RedJavaBitInputStream(is, JavaBitStreamMode.SIMPLE_BYTE);
    }

    @Override
    public JavaBitInputStream newBitInputStream(java.io.InputStream is, JavaBitStreamMode mode) {
	return new RedJavaBitInputStream(is, mode);
    }

    @Override
    public JavaBitOutputStream newBitOutputStream(java.io.OutputStream os, JavaBitStreamMode mode) {
	return new RedJavaBitOutputStream(os, mode);
    }

    @Override
    public JavaBitOutputStream newBitOutputStream(java.io.OutputStream os) {
	return new RedJavaBitOutputStream(os, JavaBitStreamMode.SIMPLE_BYTE);
    }

    @Override
    public void writeShort(java.io.OutputStream javaOutputStream, int value) throws IOException {
	javaOutputStream.write((value >> 8 * 0) & 0xff);
	javaOutputStream.write((value >> 8 * 1) & 0xff);

    }

    @Override
    public ByteArray serialize(Serializable object) throws IOException {
	ByteArrayOutputStream buff = new ByteArrayOutputStream();
	ObjectOutputStream os = new ObjectOutputStream(buff);
	os.writeObject(object);
	os.flush();
	os.close();
	buff.close();
	return JUtils.newByteArray(buff.toByteArray());
    }

    @Override
    public void serialize(Serializable object, OutputStream output_stream) throws IOException {
	java.io.OutputStream jos = output_stream.toJavaOutputStream();
	ObjectOutputStream os = new ObjectOutputStream(jos);
	os.writeObject(object);
	os.flush();
	jos.flush();
    }

    @Override
    public <T> T deserialize(Class<T> type, InputStream input_stream) throws IOException {
	Debug.checkNull("input_stream", input_stream);
	Debug.checkNull("type", type);
	java.io.InputStream jis = input_stream.toJavaInputStream();
	ObjectInputStream os = new ObjectInputStream(jis);
	try {
	    T object = (T) os.readObject();
	    return object;
	} catch (Throwable e) {
	    throw new IOException(e);
	} finally {
	    // forceClose(jis);
	    // forceClose(os);
	}
    }

    @Override
    public <T> T deserialize(Class<T> type, ByteArray bytes) throws IOException {
	Debug.checkNull("bytes", bytes);
	Debug.checkNull("type", type);
	ByteArrayInputStream jis = new ByteArrayInputStream(bytes.toArray());
	ObjectInputStream os = new ObjectInputStream(jis);
	try {
	    T object = (T) os.readObject();
	    return object;
	} catch (Throwable e) {
	    throw new IOException(e);
	} finally {
	    forceClose(jis);
	    forceClose(os);
	}
    }

}
