
package com.jfixby.scarabei.red.gwt.collections;

import java.util.ArrayList;
import java.util.Comparator;

import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.red.collections.RedList;

public class GwtList<T> extends RedList<T> implements List<T> {

	public GwtList () {
		super(new ArrayList<T>());
	}

	@Override
	public void sort () {
		this.sort(null);
	}

	@Override
	public void sort (final Comparator<? super T> comparator) {
		this.legacy.sort(comparator);
	}
}
