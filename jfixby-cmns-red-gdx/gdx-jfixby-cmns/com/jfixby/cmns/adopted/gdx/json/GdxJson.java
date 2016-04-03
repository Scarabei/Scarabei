package com.jfixby.cmns.adopted.gdx.json;

import com.jfixby.cmns.api.json.JsonComponent;
import com.jfixby.cmns.api.json.JsonString;
import com.jfixby.cmns.api.log.L;

public class GdxJson implements JsonComponent {

    @Override
    public JsonString serializeToString(Object object) {
	Json json = new Json();
	json.setOutputType(OutputType.json);
	JsonString data = json.toJson(object);
	JsonValue gdx_json = new JsonReader().parse(data);
	data = gdx_json.prettyPrint(OutputType.json, 0);
	return data;
    }

    @Override
    public <T> T deserializeFromString(Class<T> type, String raw_json_string) {
	Json json = new Json();
	try {
	    T object = json.fromJson(type, new GdxJsonString(raw_json_string));
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

}
