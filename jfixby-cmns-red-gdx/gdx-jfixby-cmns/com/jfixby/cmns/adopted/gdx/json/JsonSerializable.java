package com.jfixby.cmns.adopted.gdx.json;

public interface JsonSerializable {
	public void write (GdxSerialization json);

	public void read (GdxSerialization json, JsonValue jsonData);
}