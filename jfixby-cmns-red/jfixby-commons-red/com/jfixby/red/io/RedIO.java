package com.jfixby.red.io;

import java.io.IOException;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.io.Buffer;
import com.jfixby.cmns.api.io.BufferInputStream;
import com.jfixby.cmns.api.io.BufferOutputStream;
import com.jfixby.cmns.api.io.IOComponent;
import com.jfixby.cmns.api.io.InputStream;
import com.jfixby.cmns.api.io.OutputStream;
import com.jfixby.cmns.api.io.StreamPipe;
import com.jfixby.cmns.api.io.U_StreamPipeProgressListener;
import com.jfixby.cmns.api.json.Json;

public class RedIO implements IOComponent {

    @Override
    public StreamPipe newStreamPipe(InputStream input_stream, OutputStream output_stream,
	    U_StreamPipeProgressListener progress_listener) {
	return new RedStreamPipe(input_stream, output_stream, progress_listener);
    }

    @Override
    public void serialize(Object object, OutputStream output_stream) throws IOException {
	String data_string = Json.serializeToString(object);
	byte[] bytes = data_string.getBytes();
	output_stream.write(bytes);
	output_stream.flush();
    }

    @Override
    public <T> T deserialize(Class<T> type, InputStream input_stream) throws IOException {
	byte[] bytes = input_stream.readAll();
	String data_string = new String(bytes, "UTF-8");
	T object = Json.deserializeFromString(type, data_string);
	return object;
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
	byte[] bytes = input_stream.readAll();

	return new RedBuffer(bytes);
    }

    @Override
    public Buffer newBuffer(byte[] bytes) {
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
    public byte readByte(java.io.InputStream javaInputStream) throws IOException {
	return (byte) javaInputStream.read();
    }

    @Override
    public int readInt(final java.io.InputStream javaInputStream) throws IOException {
	return (readByte(javaInputStream) << 8 * 3) | //
		(readByte(javaInputStream) << 8 * 2) | //
		(readByte(javaInputStream) << 8 * 1) | //
		(readByte(javaInputStream) << 8 * 0);//
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

}
