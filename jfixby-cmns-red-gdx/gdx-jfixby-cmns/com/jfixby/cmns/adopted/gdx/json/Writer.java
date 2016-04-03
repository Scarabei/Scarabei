package com.jfixby.cmns.adopted.gdx.json;

import java.io.IOException;

public abstract interface Writer {

    void write(int c);

    void write(String quoteName);

    void flush() throws IOException;

    void write(char[] cbuf, int off, int len) throws IOException;

    void close() throws IOException;

}
