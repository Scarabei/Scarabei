
package com.jfixby.scarabei.red.input;

import java.util.ArrayList;

import com.jfixby.scarabei.api.input.Key;
import com.jfixby.scarabei.api.input.KeysList;

public class RedKeysList implements KeysList {

	final private ArrayList<Key> list = new ArrayList<Key>();
// final private ArrayList<Boolean> pressed = new ArrayList<Boolean>();

	public void add (final Key keyCode) {
		this.list.add(keyCode);
	}

	@Override
	public int size () {
		return this.list.size();
	}

	@Override
	public Key get (final int i) {
		return this.list.get(i);
	}

	@Override
	public int indexOf (final Key otherKey) {
		return this.list.indexOf(otherKey);
	}

}
