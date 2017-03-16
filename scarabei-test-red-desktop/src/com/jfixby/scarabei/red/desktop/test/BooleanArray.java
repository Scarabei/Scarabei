
package com.jfixby.scarabei.red.desktop.test;

public class BooleanArray {

	private static final int SECTION_SIZE = 32;
	final int[] array;
	private int actual_size;
	private int size;

	public BooleanArray (long n) {
		size = (int)n;
		actual_size = 1 + (int)(n / SECTION_SIZE);
		array = new int[actual_size];
	}

	public boolean get (int i) {
		final int k = i / SECTION_SIZE;
		final int offset = i % SECTION_SIZE;

		final int val = array[k];

		final int result = (int)((val >> offset) & 0x1);

// L.d("get", Integer.toBinaryString(array[k]));
		return result == 1;
	}

	public void set (int i, boolean flag) {
		int bit = 1;
		if (!flag) {
			bit = 0;
		}
		final int k = i / SECTION_SIZE;
		final int offset = i % SECTION_SIZE;
		final int val = array[k];
		final int app = (bit << offset);
// L.d("app " + i, Integer.toBinaryString(app));
		final int result = app | val;
		array[k] = result;
// L.d("set " + i, Integer.toBinaryString(array[k]));
	}

	@Override
	public String toString () {
		StringBuilder buffer = new StringBuilder();

		buffer.append("[");
		// for (int i = 0; i < this.size; i++) {
		// buffer.append(this.get(i) + " ");
		// }

		for (int i = 0; i < this.actual_size; i++) {
			buffer.append(Integer.toBinaryString(array[i]));
		}
		buffer.append("]");
		return buffer.toString();
	}

}
