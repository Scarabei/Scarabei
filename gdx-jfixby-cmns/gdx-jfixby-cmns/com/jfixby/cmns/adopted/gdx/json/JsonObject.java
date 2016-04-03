package com.jfixby.cmns.adopted.gdx.json;

import java.io.IOException;

class JsonObject {
    /**
     * 
     */
    private final JsonWriter jsonWriter;
    final boolean array;
    boolean needsComma;

    JsonObject(JsonWriter jsonWriter, boolean array) throws IOException {
        this.jsonWriter = jsonWriter;
        this.array = array;
        this.jsonWriter.writer.write(array ? '[' : '{');
    }

    void close() throws IOException {
        this.jsonWriter.writer.write(array ? ']' : '}');
    }
}