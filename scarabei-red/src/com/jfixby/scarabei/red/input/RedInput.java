
package com.jfixby.scarabei.red.input;

import com.jfixby.scarabei.api.input.UserInputComponent;

public class RedInput implements UserInputComponent {

	final RedKeyboard keyboard = new RedKeyboard();
	final RedMouseButtons mouse = new RedMouseButtons();

	@Override
	public com.jfixby.scarabei.api.input.Keyboard Keyboard () {
		return keyboard;
	}

	@Override
	public com.jfixby.scarabei.api.input.MouseButtons MouseButtons () {
		return mouse;
	}

}
