package com.jfixby.red.input;

import com.jfixby.cmns.api.input.UserInputComponent;

public class RedInput implements UserInputComponent {

	final RedKeyboard keyboard = new RedKeyboard();
	final RedMouseButtons mouse = new RedMouseButtons();

	@Override
	public com.jfixby.cmns.api.input.Keyboard Keyboard() {
		return keyboard;
	}

	@Override
	public com.jfixby.cmns.api.input.MouseButtons MouseButtons() {
		return mouse;
	}

}
