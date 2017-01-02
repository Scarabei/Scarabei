
package com.jfixby.scarabei.red.desktop.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class GenericSort {

	@Test
	public void test () {

		final ArrayList<Double> input = new ArrayList<>();
		final ArrayList<Double> test = new ArrayList<>();
		input.add(3d);
		input.add(2d);
		input.add(1d);
		input.add(0d);
		input.add(0d);

		test.addAll(input);

		Collections.sort(test);
		sort(input);

		this.log(test);
		this.log(input);

	}

	private void log (final ArrayList<Double> test) {
		System.out.println(test);
	}

	public static <T extends Comparable<? super T>> void sort (final List<T> list) {
		sortComparable((List<Comparable<T>>)list);
	}

	public static final <T> void sortComparable (final List<Comparable<T>> input) {
		// сортируем лист компараблов
	}

}
