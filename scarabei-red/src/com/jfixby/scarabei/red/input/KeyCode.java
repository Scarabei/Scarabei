
package com.jfixby.scarabei.red.input;

import com.jfixby.scarabei.api.input.Key;

public final class KeyCode implements Key {
	final String name;

	KeyCode (final String name, final RedKeyboard keyboard) {
		this.name = name;
		final RedKeysList list = keyboard.KEYS_LIST;
		list.add(this);
	}

	@Override
	public String toString () {
		return "KeyCode [" + this.name + "]";
	}

	@Override
	public String getName () {
		return this.name;
	}

	@Override
	public boolean is (final Key other) {
		return this == other;
	}

}
