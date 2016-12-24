package com.jfixby.scarabei.adopted.gdx.io;

import java.io.Closeable;
import java.io.IOException;

import com.jfixby.scarabei.api.json.JsonString;

public interface DataWriter<T> extends Closeable {

    DataWriter<T> object() throws IOException;

    DataWriter<T> pop() throws IOException;

    DataWriter<T> writeName(String name) throws IOException;

    DataWriter<T> array() throws IOException;

    DataWriter<T> set(String name, Object value) throws IOException;

    DataWriter<T> value(Object value) throws IOException;

    T toOutputData();

}
