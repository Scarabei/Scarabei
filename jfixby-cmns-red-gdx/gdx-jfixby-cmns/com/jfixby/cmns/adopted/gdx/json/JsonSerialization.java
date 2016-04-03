package com.jfixby.cmns.adopted.gdx.json;

import com.jfixby.cmns.api.json.JsonString;

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
