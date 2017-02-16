
package com.jfixby.scarabei.red.desktop.test.generic_user;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;

public class Test {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();

		final List<Double> x = Collections.newList();

		x.add(0.5d);
		x.add(0.5d);
		x.add(0.5d);
		x.add(0.5d);

		x.print("x");

		final Collection<Comparable<Double>> y = (Collection<Comparable<Double>>)(Collection<? extends Comparable<Double>>)x;
		sort(y);

// (Collection<Comparable<Double>>)(Collection<? super Double>)x
// final Collection<Comparable<Double> super Double> o = x;

// final Collection<? super Comparable<Double>> o = x;
// final Collection<Comparable<Object>> y = (Collection<Comparable<Object>>)o;

// y.print("y");

// final Map<X, X> xx = Collections.newMap();
// xx.put(new X(), new X());
// xx.put(new X(), new X());
// xx.put(new X(), new X());
//
// final Map<? extends Y, ? extends Y> yy = xx;
// yy.print("yy");
//
// final Map<Y, Y> YY = (Map<Y, Y>)yy;
// YY.print("YY");
// Map<Y, Y> yy ;

// Collections.castCollection(null);

// final BadList bad = new BadList();
//
// final List<Y> list = bad.getList();
// L.d(list);
// final List<Y> list2 = list;
// final Y y = list.get(0);

	}

	public static final <T> void sort (final Collection<Comparable<T>> input) {

	}

}
