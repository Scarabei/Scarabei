
package com.jfixby.scarabei.adopted.gdx.json.original;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.jfixby.scarabei.adopted.gdx.json.red.GdxJsonString;
import com.jfixby.scarabei.api.json.JsonComponent;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;

public class OriginalGdxJson implements JsonComponent {

	@Override
	public JsonString serializeToString (Object object) {
		Json json = new Json();
		String data = json.toJson(object);
		data = json.prettyPrint(data);
		JsonValue gdx_json = new JsonReader().parse(data);
		data = gdx_json.prettyPrint(OutputType.json, 0);
		return newJsonString(data);
	}

	@Override
	public <T> T deserializeFromString (Class<T> type, String raw_json_string) {
		Json json = new Json();
		try {
			T object = json.fromJson(type, raw_json_string);
			return object;
		} catch (Exception e) {
			L.d(e);
			L.e("input string", raw_json_string);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void printPretty (JsonString json_string) {
		JsonValue gdx_json = new JsonReader().parse(json_string.toString());
		L.d(gdx_json.toString());
	}

	@Override
	public <T> T deserializeFromString (Class<T> type, JsonString input_data) {
		return this.deserializeFromString(type, input_data.toString());
	}

	@Override
	public JsonString newJsonString (String raw_json_string) {
		return new GdxJsonString(raw_json_string);
	}
}
