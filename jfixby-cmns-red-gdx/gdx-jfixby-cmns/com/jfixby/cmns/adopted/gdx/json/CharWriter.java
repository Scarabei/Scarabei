package com.jfixby.cmns.adopted.gdx.json;

import java.io.IOException;

public abstract interface CharWriter {

    void write(int cchar);

    void write(String quoteName);

    void flush() throws IOException;

    void write(char[] cbuf, int off, int len) throws IOException;

    void close() throws IOException;

}
