
package com.jfixby.scarabei.red.collections;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.ZxZ_Functuion;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.math.Int2;
import com.jfixby.scarabei.api.math.IntegerMath;

public class Red_ZxZ_Map_Functuion<T> implements ZxZ_Functuion<T> {

	final Map<Int2, T> mapping = Collections.newMap();

	@Override
	public T getValueAt (final long x, final long y) {

		{
			final Int2 position = IntegerMath.newInt2(x, y);
			return this.mapping.get(position);
		}

		// int position = (int) IntegerMath.ZxZtoN(x, y);
		// if (position >= base.size()) {
		// return null;
		// }
		// return this.base.getElementAt(position);
	}

	int N = 10;

	@Override
	public void setValueAt (final long x, final long y, final T value) {
		final Int2 position = IntegerMath.newInt2(x, y);
		this.mapping.put(position, value);
		//
		// int position = (int) IntegerMath.ZxZtoN(x, y);
		// while (position >= base.size()) {
		// N = N * 4;
		// for (int i = 0; i < N; i++) {
		// this.base.add(null);
		// }
		// }
		// this.base.setElementAt(value, position);
	}

	@Override
	public T removeElementAt (final long x, final long y) {
		final Int2 position = IntegerMath.newInt2(x, y);
		return this.mapping.remove(position);

		// int position = (int) IntegerMath.ZxZtoN(x, y);
		// if (position >= base.size()) {
		// return null;
		// }
		// return this.base.removeElementAt(position);
	}

	@Override
	public Collection<T> getAllValues () {
		final Collection<T> collection = this.mapping.values();
		return collection;
	}

	@Override
	public void print (final String tag) {
// this.mapping.print(tag);
		Err.throwNotImplementedYet();
	}
}
