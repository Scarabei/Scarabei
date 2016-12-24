package com.jfixby.scarabei.adopted.gdx.json.red;

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
        this.jsonWriter.buffer.write(array ? '[' : '{');
    }

    void close() throws IOException {
        this.jsonWriter.buffer.write(array ? ']' : '}');
    }
}