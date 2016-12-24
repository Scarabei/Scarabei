package com.jfixby.scarabei.adopted.gdx.io;

public interface JsonSerializer<T> {
	public void write (GdxSerialization json, T object, Class knownType);

	public T read (GdxSerialization json, JsonValue jsonData, Class type);
}