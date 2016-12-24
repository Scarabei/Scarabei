
package com.jfixby.scarabei.red.input;

import com.jfixby.scarabei.api.input.MouseButton;

public final class RedMouseButton implements MouseButton {

	final String name;

	RedMouseButton (String name) {
		this.name = name;

	}

	@Override
	public String toString () {
		return "MouseButton [" + name + "]";
	}

	@Override
	public String getName () {
		return name;
	}

}
