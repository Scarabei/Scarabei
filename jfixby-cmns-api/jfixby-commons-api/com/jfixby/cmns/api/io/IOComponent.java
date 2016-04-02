package com.jfixby.cmns.api.io;

import java.io.Closeable;
import java.io.IOException;

import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.cmns.api.java.ByteArray;

public interface IOComponent {

    StreamPipe newStreamPipe(InputStream input_stream, OutputStream output_stream,
	    U_StreamPipeProgressListener progress_listener);

    void serialize(Object object, OutputStream output_stream) throws IOException;

    public <T> T deserialize(Class<T> type, InputStream input_stream) throws IOException;

    Buffer newBuffer();

    BufferOutputStream newBufferOutputStream();

    Buffer readStreamToBuffer(InputStream is) throws IOException;

    Buffer newBuffer(ByteArray bytes);

    void writeBufferToStream(Buffer buffer, FileOutputStream os) throws IOException;

    BufferInputStream newBufferInputStream(Buffer buffer);

    public InputStream toInputStream(java.io.InputStream java_input_stream) throws IOException;

    public OutputStream toOutputStream(java.io.OutputStream java_output_stream) throws IOException;

    int readByte(java.io.InputStream javaInputStream) throws IOException;

    int readInt(java.io.InputStream javaInputStream) throws IOException;

    public void writeInt(java.io.OutputStream javaOutputStream, int value) throws IOException;

    public void writeByte(java.io.OutputStream javaOutputStream, int value) throws IOException;

    void forceClose(ForceCloseable os);

    GZipOutputStream newGZipStream(OutputStream os) throws IOException;

    GZipInputStream newGZipStream(InputStream is) throws IOException;

    void writeBytes(java.io.OutputStream javaOutputStream, int[] bytes) throws IOException;

    void forceClose(Closeable os);

    void readBytes(java.io.InputStream javaInputStream, int[] array) throws IOException;

    JavaBitInputStream newBitInputStream(java.io.InputStream is);

    JavaBitInputStream newBitInputStream(java.io.InputStream is, JavaBitStreamMode mode);

    JavaBitOutputStream newBitOutputStream(java.io.OutputStream os, JavaBitStreamMode mode);

    JavaBitOutputStream newBitOutputStream(java.io.OutputStream os);

    void writeShort(java.io.OutputStream javaOutputStream, int value) throws IOException;

    int readShort(java.io.InputStream javaInputStream) throws IOException;

    // public String deserializeFromString(String from_string) throws
    // IOException;

    // Object deserialize(FileInputStream output_stream,Class<?> type);

}
