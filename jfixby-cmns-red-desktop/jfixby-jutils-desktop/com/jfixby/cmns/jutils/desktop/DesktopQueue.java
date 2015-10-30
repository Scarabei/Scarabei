package com.jfixby.cmns.jutils.desktop;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Queue;

public class DesktopQueue<T> extends DesktopList<T> implements Queue<T> {

	@Override
	public void push(T element) {
		this.add(element);
	}

	@Override
	public boolean hasMore() {
		return this.size() > 0;
	}

	@Override
	public T pop() {
		return this.removeElementAt(0);
	}

	@Override
	public void pushAll(Collection<T> elements) {
		super.addAll(elements);
	}
}
