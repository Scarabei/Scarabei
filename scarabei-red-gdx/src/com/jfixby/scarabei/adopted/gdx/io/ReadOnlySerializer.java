package com.jfixby.scarabei.adopted.gdx.io;

abstract public class ReadOnlySerializer<T> implements JsonSerializer<T> {
	public void write (GdxSerialization json, T object, Class knownType) {
	}

	abstract public T read (GdxSerialization json, JsonValue jsonData, Class type);
}