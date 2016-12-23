
package com.jfixby.red.input;

import java.util.ArrayList;

import com.jfixby.cmns.api.input.Key;
import com.jfixby.cmns.api.input.KeysList;

public class RedKeysList implements KeysList {

	final private ArrayList<Key> list = new ArrayList<Key>();

	public void add (final Key keyCode) {
		list.add(keyCode);
	}

	@Override
	public int size () {
		return this.list.size();
	}

	@Override
	public Key get (int i) {
		return this.list.get(i);
	}

}
