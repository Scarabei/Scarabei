package com.jfixby.red.android.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.red.collections.RedList;

public class AndroidList<T> extends RedList<T> implements List<T> {

	public AndroidList() {
		super(new ArrayList<T>());
	}

	@Override
	public void sort() {
		this.sort(null);
	}

	@Override
	public void sort(final Comparator<T> comparator) {
		Collections.sort(legacy, comparator);
	}
}
