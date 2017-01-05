
package com.jfixby.scarabei.adopted.gdx.json.red;

import com.jfixby.scarabei.adopted.gdx.io.JsonValue;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonComponent;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;

public class RedGdxJson implements JsonComponent {

	@Override
	public JsonString serializeToString (final Object object) {
		final JsonSerialization json = new JsonSerialization();

		JsonString data = json.serialize(object);
		final JsonValue gdx_json = new JsonReader().parse(data);
		data = gdx_json.prettyPrint(JsonOutputType.json, 0);
		return data;
	}

	@Override
	public <T> T deserializeFromString (final Class<T> type, final String raw_json_string) {

		final JsonSerialization json = new JsonSerialization();

		try {
			final T object = json.deSerialize(type, Json.newJsonString(raw_json_string));
			return object;
		} catch (final Exception e) {
			L.d(e);
			L.e("input string", raw_json_string);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void printPretty (final JsonString json_string) {
		final JsonValue gdx_json = new JsonReader().parse(json_string);
		L.d(gdx_json.toString());
	}

	@Override
	public <T> T deserializeFromString (final Class<T> type, final JsonString input_data) {
		return this.deserializeFromString(type, input_data.toString());
	}

	@Override
	public JsonString newJsonString (final String raw_json_string) {
		return new GdxJsonString(raw_json_string);
	}
}
