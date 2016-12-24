package com.jfixby.scarabei.adopted.gdx.json.red;

import com.jfixby.scarabei.adopted.gdx.io.JsonValue;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonComponent;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;

public class RedGdxJson implements JsonComponent {

    @Override
    public JsonString serializeToString(Object object) {
	JsonSerialization json = new JsonSerialization();

	JsonString data = json.serialize(object);
	JsonValue gdx_json = new JsonReader().parse(data);
	data = gdx_json.prettyPrint(JsonOutputType.json, 0);
	return data;
    }

    @Override
    public <T> T deserializeFromString(Class<T> type, String raw_json_string) {

	JsonSerialization json = new JsonSerialization();

	try {
	    T object = json.deSerialize(type, Json.newJsonString(raw_json_string));
	    return object;
	} catch (Exception e) {
	    L.d(e);
	    L.e("input string", raw_json_string);
	    e.printStackTrace();
	    return null;
	}
    }

    @Override
    public void printPretty(JsonString json_string) {
	JsonValue gdx_json = new JsonReader().parse(json_string);
	L.d(gdx_json.toString());
    }

    @Override
    public <T> T deserializeFromString(Class<T> type, JsonString input_data) {
	return this.deserializeFromString(type, input_data.toString());
    }

    @Override
    public JsonString newJsonString(String raw_json_string) {
	return new GdxJsonString(raw_json_string);
    }
}