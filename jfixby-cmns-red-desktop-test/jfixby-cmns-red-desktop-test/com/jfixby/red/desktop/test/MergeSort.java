
package com.jfixby.red.desktop.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class MergeSort {

	@Test
	public void testReverse () {

		assertTrue(this.checkSorting(new byte[] {}));
		assertTrue(this.checkSorting(new byte[] {1}));
		assertTrue(this.checkSorting(new byte[] {1, 2}));
		assertTrue(this.checkSorting(new byte[] {2, 1}));
		assertTrue(this.checkSorting(new byte[] {1, 2, 1}));
		assertTrue(this.checkSorting(randomArray(100)));
		for (int k = 0; k < 100; k++) {
			assertTrue(this.checkSorting(randomArray(k)));
		}

	}

	private boolean checkSorting (final byte[] list) {
		System.out.println("checkSorting " + "(" + Arrays.toString(list) + ")");

		final byte[] java_array = this.copy(list);
		final byte[] merge_array = this.copy(list);

		Arrays.sort(java_array);
		mergeSort(merge_array);

		final boolean areEqual = Arrays.equals(java_array, merge_array);
		if (!areEqual) {
			System.out.println("Failed to sort " + "(" + Arrays.toString(list) + ")");
			System.out.println("        result" + "(" + Arrays.toString(merge_array) + ")");
			System.out.println("     should be" + "(" + Arrays.toString(java_array) + ")");
		} else {
			System.out.println("Success to sort " + "(" + Arrays.toString(list) + ")");
			System.out.println("        result" + "(" + Arrays.toString(merge_array) + ")");
			System.out.println("     should be" + "(" + Arrays.toString(java_array) + ")");
			System.out.println();
		}
		return areEqual;
	}

	private byte[] copy (final byte[] src) {
		final byte[] copy = new byte[src.length];
		System.arraycopy(src, 0, copy, 0, src.length);
		return copy;
	}

	public static byte[] randomArray (final int N) {
		final byte[] random = new byte[N]; // create the Array with N slots
		final Random rnd = new Random(4); // create a local variable for Random
		for (int i = 0; i < random.length; i++) // filling with randoms
		{
			random[i] = ((byte)rnd.nextInt());
		}
		return random;
	}

	private static void mergeSort (final byte[] list) {
		final byte[] tmp = new byte[list.length];
		mergeSort(0, list.length, list, tmp);
	}

	private static void mergeSort (final long begin, final long end, final byte[] list, final byte[] tmp) {
		if (end - begin < 2) {
			return;
		}
		final long mid = mid(begin, end);
		mergeSort(begin, mid, list, tmp);
		mergeSort(mid, end, list, tmp);
		merge(begin, mid, end, list, tmp);
	}

	private static void merge (final long begin, final long mid, final long end, final byte[] list, final byte[] tmp) {
		final int index0 = (int)begin;
		int indexA = (int)begin;
		final int maxA = (int)mid;
		int indexB = (int)mid;
		final int maxB = (int)end;
		final int tmpSize = (int)(end - begin);

		int tmpI = 0;
		while (indexA < maxA && indexB < maxB) {
			final byte valueA = list[indexA];
			final byte valueB = list[indexB];
			if (valueA <= valueB) {
				tmp[tmpI] = valueA;
				indexA++;
			} else {
				tmp[tmpI] = valueB;
				indexB++;
			}
			tmpI++;
		}
		while (indexA < maxA) {
			final byte valueA = list[indexA];
			tmp[tmpI] = valueA;
			indexA++;
			tmpI++;
		}
		while (indexB < maxB) {
			final byte valueB = list[indexB];
			tmp[tmpI] = valueB;
			indexB++;
			tmpI++;
		}
		for (int i = 0; i < tmpSize; i++) {
			final int index = index0 + i;
			list[index] = tmp[i];
		}

	}

	private static long mid (final long begin, final long end) {
		return (end + begin) / 2;
	}

}
