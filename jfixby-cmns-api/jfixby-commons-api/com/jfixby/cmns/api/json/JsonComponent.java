
package com.jfixby.cmns.api.json;

public interface JsonComponent {

	// void serialize(Object object, OutputStream output_stream) throws
	// IOException;

	// <T> T deserialize(Class<T> type, InputStream input_stream) throws
	// IOException;

	JsonString serializeToString (Object object);

	public <T> T deserializeFromString (Class<T> type, JsonString input_data);

	public <T> T deserializeFromString (Class<T> type, String raw_json_string);

	void printPretty (JsonString json_string);

	JsonString newJsonString (String raw_json_string);

}
