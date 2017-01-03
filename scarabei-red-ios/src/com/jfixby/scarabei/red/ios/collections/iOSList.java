
package com.jfixby.scarabei.red.ios.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.red.collections.RedList;

public class iOSList<T> extends RedList<T> implements List<T> {

	public iOSList () {
		super(new ArrayList<T>());
	}

	@Override
	public void sort () {
		this.sort(null);
	}

	@Override
	public void sort (final Comparator<? super T> comparator) {
		Collections.sort(this.legacy, comparator);
	}

}
