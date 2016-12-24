package com.jfixby.scarabei.adopted.gdx.json.red;

import com.jfixby.scarabei.adopted.gdx.io.DataWriter;
import com.jfixby.scarabei.adopted.gdx.io.GdxSerialization;
import com.jfixby.scarabei.api.json.JsonString;

public class JsonSerialization extends GdxSerialization<JsonString> {

    private JsonWriter jsonWriter;

    public JsonSerialization() {
	super();

	jsonWriter = new JsonWriter();
	jsonWriter.setOutputType(JsonOutputType.json);
	jsonWriter.setQuoteLongValues(false);

	DataWriter<JsonString> writer = jsonWriter;

	this.setWriter(writer);

    }

    public <T> T deSerialize(Class<T> type, JsonString newJsonString) {
	JsonReader reader = new JsonReader();
	return this.deSerialize(type, newJsonString, reader);
    }

}
