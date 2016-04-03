package com.jfixby.cmns.adopted.gdx.json;

public interface JsonSerializable {
	public void write (GdxJson json);

	public void read (GdxJson json, JsonValue jsonData);
}