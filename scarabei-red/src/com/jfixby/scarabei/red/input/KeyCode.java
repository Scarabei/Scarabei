
package com.jfixby.scarabei.red.input;

import com.jfixby.scarabei.api.input.Key;

public final class KeyCode implements Key {
	final String name;

	KeyCode (String name, RedKeyboard keyboard) {
		this.name = name;
		final RedKeysList list = (RedKeysList)keyboard.KEYS_LIST;
		list.add(this);
	}

	@Override
	public String toString () {
		return "KeyCode [" + name + "]";
	}

	public String getName () {
		return this.name;
	}

}
