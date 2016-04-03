package com.jfixby.cmns.adopted.gdx.json;

public interface JsonSerializer<T> {
	public void write (GdxJson json, T object, Class knownType);

	public T read (GdxJson json, JsonValue jsonData, Class type);
}