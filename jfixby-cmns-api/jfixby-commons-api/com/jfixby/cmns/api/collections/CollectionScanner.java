package com.jfixby.cmns.api.collections;

public interface CollectionScanner<T > {

	public void scanElement(T element, int index, Collection<? extends T> collection);

}
