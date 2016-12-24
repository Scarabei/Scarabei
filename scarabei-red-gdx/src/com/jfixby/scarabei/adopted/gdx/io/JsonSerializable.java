package com.jfixby.scarabei.adopted.gdx.io;

public interface JsonSerializable {
	public void write (GdxSerialization json);

	public void read (GdxSerialization json, JsonValue jsonData);
}