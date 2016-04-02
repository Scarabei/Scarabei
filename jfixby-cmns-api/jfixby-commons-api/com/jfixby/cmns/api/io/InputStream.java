package com.jfixby.cmns.api.io;

import java.io.IOException;

import com.jfixby.cmns.api.java.ByteArray;

public interface InputStream extends ForceCloseable {

    boolean hasData() throws IOException;

    Data read() throws IOException;

    int available() throws IOException;

    void close() throws IOException;

    ByteArray readAll() throws IOException;

    java.io.InputStream toJavaInputStream();

}
