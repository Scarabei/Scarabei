package com.jfixby.cmns.api.json;

public interface JsonComponent {

	// void serialize(Object object, OutputStream output_stream) throws
	// IOException;

	// <T> T deserialize(Class<T> type, InputStream input_stream) throws
	// IOException;

	String serializeToString(Object object);

	public <T> T deserializeFromString(Class<T> type, String input_data);

	void printPretty(String json_string);

}
