package com.jfixby.cmns.jutils.desktop;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.collections.ZxZ_Functuion;
import com.jfixby.cmns.api.math.IntegerMath;

public class Desktop_ZxZ_Array_Functuion<T> implements ZxZ_Functuion<T> {

	final private List<T> base = JUtils.newList();

	@Override
	public T getValueAt(long x, long y) {

		long position = (long) IntegerMath.ZxZtoN(x, y);
		if (position >= base.size()) {
			return null;
		}
		return this.base.getElementAt(position);
	}

	int N = 10;

	@Override
	public void setValueAt(long x, long y, T value) {

		int position = (int) IntegerMath.ZxZtoN(x, y);
		while (position >= base.size()) {
			N = N * 4;
			for (int i = 0; i < N; i++) {
				this.base.add(null);
			}
		}
		this.base.setElementAt(value, position);
	}

	@Override
	public T removeElementAt(long x, long y) {

		int position = (int) IntegerMath.ZxZtoN(x, y);
		if (position >= base.size()) {
			return null;
		}
		return this.base.replaceElementAt(position, null);
	}

	@Override
	public List<T> getAllValues() {
		Set<T> tmp = JUtils.newSet();
		tmp.addAll(base);
		tmp.remove(null);
		return tmp.toList();
	}

	@Override
	public void print(String tag) {
		this.base.print(tag);
	}
}
