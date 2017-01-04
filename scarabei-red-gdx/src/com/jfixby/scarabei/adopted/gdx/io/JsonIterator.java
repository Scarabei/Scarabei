
package com.jfixby.scarabei.adopted.gdx.io;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JsonIterator implements Iterator<JsonValue>, Iterable<JsonValue> {
	/**
	  *
	  */
	private final JsonValue jsonValue;

	/** @param jsonValue */
	JsonIterator (final JsonValue jsonValue) {
		this.jsonValue = jsonValue;
		this.entry = this.jsonValue.child;
	}

	JsonValue entry;
	JsonValue current;

	@Override
	public boolean hasNext () {
		return this.entry != null;
	}

	@Override
	public JsonValue next () {
		this.current = this.entry;
		if (this.current == null) {
			throw new NoSuchElementException();
		}
		this.entry = this.current.next;
		return this.current;
	}

	@Override
	public void remove () {
		if (this.current.prev == null) {
			this.jsonValue.child = this.current.next;
			if (this.jsonValue.child != null) {
				this.jsonValue.child.prev = null;
			}
		} else {
			this.current.prev.next = this.current.next;
			if (this.current.next != null) {
				this.current.next.prev = this.current.prev;
			}
		}
		this.jsonValue.size--;
	}

	@Override
	public Iterator<JsonValue> iterator () {
		return this;
	}
}
