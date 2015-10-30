package com.jfixby.cmns.api.collections;

public interface List<T> extends EditableCollection<T> {

	public void insertElementAt(T element, int iindex);

	public void setElementAt(T element, int iindex);

	public T replaceElementAt(int index, T replacement);

}
