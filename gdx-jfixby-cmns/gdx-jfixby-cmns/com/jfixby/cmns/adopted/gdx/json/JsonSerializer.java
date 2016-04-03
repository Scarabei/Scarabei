package com.jfixby.cmns.adopted.gdx.json;

public interface JsonSerializer<T> {
	public void write (Json json, T object, Class knownType);

	public T read (Json json, JsonValue jsonData, Class type);
}