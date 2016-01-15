package com.jfixby.red.input;

import com.jfixby.cmns.api.input.MouseButton;

public final class RedMouseButton implements MouseButton {

	final String name;

	RedMouseButton(String name) {
		this.name = name;

	}

	@Override
	public String toString() {
		return "MouseButton [" + name + "]";
	}

	@Override
	public String getName() {
		return name;
	}

}
