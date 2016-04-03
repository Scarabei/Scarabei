package com.jfixby.cmns.adopted.gdx.json;

import com.jfixby.cmns.api.json.Json;
import com.jfixby.cmns.api.json.JsonComponent;
import com.jfixby.cmns.api.json.JsonString;
import com.jfixby.cmns.api.log.L;

public class RedGdxJson implements JsonComponent {

    @Override
    public JsonString serializeToString(Object object) {
	GdxJson<JsonString> json = new GdxJson<JsonString>();
	json.setOutputType(OutputTypeID.json);

	JsonWriter jsonWriter = new JsonWriter();
	jsonWriter.setOutputType(OutputTypeID.json);
	jsonWriter.setQuoteLongValues(false);

	DataWriter<JsonString> writer = jsonWriter;

	JsonString data = json.toJsonRed(object, writer);
	JsonValue gdx_json = new JsonReader<JsonString>().parse(data);
	data = gdx_json.prettyPrint(OutputTypeID.json, 0);
	return data;
    }

    @Override
    public <T> T deserializeFromString(Class<T> type, String raw_json_string) {

	// JsonWriter jsonWriter = new JsonWriter();
	// jsonWriter.setOutputType(outputTypeID);
	// jsonWriter.setQuoteLongValues(quoteLongValues);

	// DataWriter<JsonString> writer = jsonWriter;

	GdxJson<JsonString> json = new GdxJson<JsonString>();
	try {
	    T object = json.fromJson(type, Json.newJsonString(raw_json_string));
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
	JsonValue gdx_json = new JsonReader<JsonString>().parse(json_string);
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
