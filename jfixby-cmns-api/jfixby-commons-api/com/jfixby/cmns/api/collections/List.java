package com.jfixby.cmns.api.collections;

public interface List<T> extends EditableCollection<T> {

	public void insertElementAt(T element, int iindex);

	public T setElementAt(T element, int iindex);

	

}
