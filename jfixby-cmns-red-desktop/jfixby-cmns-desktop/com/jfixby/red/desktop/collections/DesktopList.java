package com.jfixby.red.desktop.collections;

import java.util.ArrayList;
import java.util.Comparator;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.red.collections.RedList;

public class DesktopList<T> extends RedList<T> implements List<T> {

	public DesktopList() {
		super(new ArrayList<T>());
	}

	@Override
	public void sort() {
		this.sort(null);
	}

	@Override
	public void sort(final Comparator<T> comparator) {
		this.legacy.sort(comparator);
	}
}
