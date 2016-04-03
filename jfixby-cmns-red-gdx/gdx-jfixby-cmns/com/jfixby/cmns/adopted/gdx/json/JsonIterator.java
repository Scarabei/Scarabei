package com.jfixby.cmns.adopted.gdx.json;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class JsonIterator implements Iterator<JsonValue>, Iterable<JsonValue> {
	/**
     * 
     */
    private final JsonValue jsonValue;

    /**
     * @param jsonValue
     */
    JsonIterator(JsonValue jsonValue) {
	this.jsonValue = jsonValue;
    }

	JsonValue entry = this.jsonValue.child;
	JsonValue current;

	public boolean hasNext () {
		return entry != null;
	}

	public JsonValue next () {
		current = entry;
		if (current == null) throw new NoSuchElementException();
		entry = current.next;
		return current;
	}

	public void remove () {
		if (current.prev == null) {
			this.jsonValue.child = current.next;
			if (this.jsonValue.child != null) this.jsonValue.child.prev = null;
		} else {
			current.prev.next = current.next;
			if (current.next != null) current.next.prev = current.prev;
		}
		this.jsonValue.size--;
	}

	public Iterator<JsonValue> iterator () {
		return this;
	}
}