
package com.jfixby.scarabei.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jfixby.scarabei.api.json.JsonComponent;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;

public class GoogleGson implements JsonComponent {

	@Override
	public JsonString serializeToString (final Object object) {
// final com.google.gson.Gson gson = new com.google.gson.Gson();
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final String jsonString = gson.toJson(object);
		return this.newJsonString(jsonString);
	}

	@Override
	public <T> T deserializeFromString (final Class<T> type, final JsonString input_data) {
		return this.deserializeFromString(type, input_data.toString());
	}

	@Override
	public <T> T deserializeFromString (final Class<T> type, final String raw_json_string) {
// final com.google.gson.Gson gson = new com.google.gson.Gson();
		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.fromJson(raw_json_string, type);
	}

	@Override
	public void printPretty (final JsonString json_string) {
		final JsonParser parser = new JsonParser();
		final JsonObject json = parser.parse(json_string.toString()).getAsJsonObject();

		final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		final String prettyJson = gson.toJson(json);
		L.d(prettyJson);
	}

	@Override
	public JsonString newJsonString (final String raw_json_string) {
		return new GoogleJsonString(raw_json_string);
	}

}
