
package com.jfixby.scarabei.red.input;

import com.jfixby.scarabei.api.input.MouseButton;
import com.jfixby.scarabei.api.input.MouseButtons;

public class RedMouseButtons implements MouseButtons {

	final MouseButton LEFT = new RedMouseButton("LEFT");

	final MouseButton RIGHT = new RedMouseButton("RIGHT");

	final MouseButton MIDDLE = new RedMouseButton("MIDDLE");

	@Override
	public MouseButton LEFT () {
		return LEFT;
	}

	@Override
	public MouseButton MIDDLE () {
		return MIDDLE;
	}

	@Override
	public MouseButton RIGHT () {
		return RIGHT;
	}

}
