
package com.jfixby.scarabei.adopted.gdx.json;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.json.JsonComponent;
import com.jfixby.scarabei.api.json.JsonString;

public class RedGson implements JsonComponent {

	@Override
	public JsonString serializeToString (final Object object) {
		Err.throwNotImplementedYet();
		return null;
	}

	@Override
	public <T> T deserializeFromString (final Class<T> type, final JsonString input_data) {
		Err.throwNotImplementedYet();
		return null;
	}

	@Override
	public <T> T deserializeFromString (final Class<T> type, final String raw_json_string) {
		Err.throwNotImplementedYet();
		return null;
	}

	@Override
	public void printPretty (final JsonString json_string) {
		Err.throwNotImplementedYet();
	}

	@Override
	public JsonString newJsonString (final String raw_json_string) {
		Err.throwNotImplementedYet();
		return null;
	}

}
