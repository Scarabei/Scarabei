package com.jfixby.cmns.adopted.gdx.json;

abstract public class ReadOnlySerializer<T> implements JsonSerializer<T> {
	public void write (GdxSerialization json, T object, Class knownType) {
	}

	abstract public T read (GdxSerialization json, JsonValue jsonData, Class type);
}