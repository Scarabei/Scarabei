package com.jfixby.red.input;

import com.jfixby.cmns.api.input.Key;

public final class RedKeyCode implements Key {
	final String name;

	RedKeyCode(String name, RedKeyboard keys) {
		this.name = name;
		final RedKeysList list = (RedKeysList) keys.KEYS_LIST;
		list.add(this);
	}

	@Override
	public String toString() {
		return "KeyCode [" + name + "]";
	}

	public String getName() {
		return this.name;
	}

}
