package com.jfixby.cmns.api.color;

public interface ColorsSet {

	void add(Color color);

	public int size();

	public Color get(int i);

	public Color findClosestTo(Color other);

}
