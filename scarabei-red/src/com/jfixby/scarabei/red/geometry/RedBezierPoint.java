
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.geometry.BezierPoint;

public class RedBezierPoint<T> implements BezierPoint<T> {

	private final T base;

	public RedBezierPoint (final T base) {
		this.base = base;
	}

	@Override
	public T valueAt (final double t) {
		return this.base;
	}

}
