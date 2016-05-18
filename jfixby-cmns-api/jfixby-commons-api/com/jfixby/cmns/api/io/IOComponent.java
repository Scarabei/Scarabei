
package com.jfixby.cmns.api.io;

import java.io.IOException;
import java.io.Serializable;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.java.ByteArray;

public interface IOComponent {

	StreamPipe newStreamPipe (InputStream input_stream, OutputStream output_stream,
		U_StreamPipeProgressListener progress_listener);

	void serialize (Serializable object, OutputStream output_stream) throws IOException;

	public <T> T deserialize (Class<T> type, InputStream input_stream) throws IOException;

	public <T> T deserialize (Class<T> type, ByteArray bytes) throws IOException;

	Buffer newBuffer ();

	BufferOutputStream newBufferOutputStream ();

	Buffer readStreamToBuffer (InputStream is) throws IOException;

	Buffer newBuffer (ByteArray bytes);

	void writeBufferToStream (Buffer buffer, FileOutputStream os) throws IOException;

	BufferInputStream newBufferInputStream (Buffer buffer);

// public OutputStream toOutputStream (java.io.OutputStream java_output_stream) throws IOException;

	int readByte (java.io.InputStream javaInputStream) throws IOException;

	int readInt (java.io.InputStream javaInputStream) throws IOException;

	public void writeInt (java.io.OutputStream javaOutputStream, int value) throws IOException;

	public void writeByte (java.io.OutputStream javaOutputStream, int value) throws IOException;

	void forceClose (java.io.OutputStream os);

	void forceClose (java.io.InputStream os);

	GZipOutputStream newGZipStream (OutputStream os) throws IOException;

	GZipInputStream newGZipStream (InputStream is) throws IOException;

	void writeBytes (java.io.OutputStream javaOutputStream, int[] bytes) throws IOException;

	void readBytes (java.io.InputStream javaInputStream, int[] array) throws IOException;

	JavaBitInputStream newBitInputStream (java.io.InputStream is);

	JavaBitInputStream newBitInputStream (java.io.InputStream is, JavaBitStreamMode mode);

	JavaBitOutputStream newBitOutputStream (java.io.OutputStream os, JavaBitStreamMode mode);

	JavaBitOutputStream newBitOutputStream (java.io.OutputStream os);

	void writeShort (java.io.OutputStream javaOutputStream, int value) throws IOException;

	int readShort (java.io.InputStream javaInputStream) throws IOException;

	ByteArray serialize (java.io.Serializable object) throws IOException;

	public OutputStream newOutputStream (OutputStreamOpener opener);

	public InputStream newInputStream (InputStreamOpener opener);

	LazyInputStream newLazyInputStream (InputStream input_stream);

}
