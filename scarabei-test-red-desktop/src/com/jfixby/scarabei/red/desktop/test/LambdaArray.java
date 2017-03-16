
package com.jfixby.scarabei.red.desktop.test;

public class LambdaArray {

	interface λ {
		int value (int x, int y);
	}

	public static void main (final String[] args) {
		final int[][] array = null;
		final λ λArray = (x, y) -> isInsideArray(x, y) ? array[x][y] : 0;
		final int N = 0;
		for (int i = 0; i < N; i++) {
			swap(array, i, i + random(N - i));
		}

	}

	private static void swap (final int[][] array, final int i, final int j) {
	}

	private static int random (final int i) {
		return 0;
	}

	private static boolean isInsideArray (final int x, final int y) {
		return false;
	}

}
