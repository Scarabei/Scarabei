package com.jfixby.red.desktop.math;

import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.api.math.Int2;
import com.jfixby.cmns.api.math.IntegerMathComponent;
import com.jfixby.red.math.RedInt2;

public class RedIntegerMath implements IntegerMathComponent {

	public long limit(long left_border, long value, long right_border) {
		if (left_border > right_border) {
			return limit(right_border, value, left_border);
		}
		if (value < left_border) {
			return left_border;
		}
		if (value > right_border) {
			return right_border;
		}
		return value;

	}

	public final long ZtoN(long z_number) {
		if (z_number < 0) {
			return -z_number * 2 - 1;
		} else {
			return z_number * 2;
		}
	}

	public final long NtoZ(long n_number) {
		checkIfItIsNatural(n_number);
		if (n_number % 2 == 0) {// positive z
			return n_number / 2;
		} else {
			return -(n_number + 1) / 2;
		}
	}

	private void checkIfItIsNatural(long n_number) {
		if (n_number < 0) {
			throw new Error("This is not natural number:" + n_number);
		}
	}

	public final long ZxZtoN(long z_x, long z_y) {
		return NxNtoN(ZtoN(z_x), ZtoN(z_y));
	}

	public final long NtoZxZgetX(long number) {
		checkIfItIsNatural(number);
		final long n_x = NtoNxNgetX(number);
		// final long n_y = NtoNxNgetY(number);
		final long z_x = NtoZ(n_x);
		// final long z_y = NtoZ(n_y);
		return z_x;
	}

	public final long NtoZxZgetY(long number) {
		checkIfItIsNatural(number);
		// final long n_x = NtoNxNgetX(number);
		final long n_y = NtoNxNgetY(number);
		// final long z_x = NtoZ(n_x);
		final long z_y = NtoZ(n_y);
		return z_y;
	}

	public final long NxNtoN(long n_x, long n_y) {
		checkIfItIsNatural(n_x);
		checkIfItIsNatural(n_y);
		final long c = n_x + n_y + 1;
		final long a = c * (c - 1) / 2;
		final long num_x_y = a + n_y;
		return num_x_y;
	}

	public final long NtoNxNgetY(long number_xy) {
		checkIfItIsNatural(number_xy);
		if (number_xy == 0) {
			return 0;
		}
		long c = FloatMath.floorDown(FloatMath.sqrt(2 * number_xy) - 1);
		long a = c * (c - 1) / 2;
		while (a <= number_xy) {
			c++;
			a = c * (c - 1) / 2;
		}
		c--;
		a = c * (c - 1) / 2;
		final long n_y = number_xy - a;
		return n_y;
	}

	public final long NtoNxNgetX(long number_xy) {
		checkIfItIsNatural(number_xy);
		if (number_xy == 0) {
			return 0;
		}
		long c = FloatMath.floorDown(FloatMath.sqrt(2 * number_xy) - 1);
		long a = c * (c - 1) / 2;
		while (a <= number_xy) {
			c++;
			a = c * (c - 1) / 2;
		}
		c--;
		a = c * (c - 1) / 2;
		final long n_y = number_xy - a;
		final long n_x = c - n_y - 1;
		return n_x;
	}

	public long min(long a, long b) {
		if (a < b) {
			return a;
		}
		return b;
	}

	public long max(long a, long b) {
		if (a < b) {
			return b;
		}
		return a;
	}

	@Override
	public int composeInteger(byte byte1, byte byte2, byte byte3, byte byte4) {
		final int B1 = setByteToInt(byte1, 3);
		final int B2 = setByteToInt(byte1, 2);
		final int B3 = setByteToInt(byte1, 1);
		final int B4 = setByteToInt(byte1, 0);
		final int result = B1 | B2 | B3 | B4;
		return result;
	}

	private int setByteToInt(byte byte_value, int byte_position) {
		int result = (((int) (byte_value) << (8 * byte_position)) & int_mask(byte_position));
		return result;
	}

	private long setByteToLong(byte byte_value, int byte_position) {
		long result = (((int) (byte_value) << (8 * byte_position)) & long_mask(byte_position));
		return result;
	}

	static final int[] INT_MASKS = new int[4];
	static final long[] LONG_MASKS = new long[8];
	static {
		INT_MASKS[0] = 0x000000ff;
		INT_MASKS[1] = 0x0000ff00;
		INT_MASKS[2] = 0x00ff0000;
		INT_MASKS[3] = 0xff000000;

		LONG_MASKS[0] = 0x00000000000000ffL;
		LONG_MASKS[1] = 0x000000000000ff00L;
		LONG_MASKS[2] = 0x0000000000ff0000L;
		LONG_MASKS[3] = 0x00000000ff000000L;
		LONG_MASKS[4] = 0x000000ff00000000L;
		LONG_MASKS[5] = 0x0000ff0000000000L;
		LONG_MASKS[6] = 0x00ff000000000000L;
		LONG_MASKS[7] = 0xff00000000000000L;
	}

	static final private int int_mask(int byte_position) {
		return INT_MASKS[byte_position];
	}

	static final private long long_mask(int byte_position) {
		return LONG_MASKS[byte_position];
	}

	@Override
	public long composeInteger(byte byte1, byte byte2, byte byte3, byte byte4,
			byte byte5, byte byte6, byte byte7, byte byte8) {
		final long B1 = setByteToLong(byte1, 7);
		final long B2 = setByteToLong(byte1, 6);
		final long B3 = setByteToLong(byte1, 5);
		final long B4 = setByteToLong(byte1, 4);
		final long B5 = setByteToLong(byte1, 3);
		final long B6 = setByteToLong(byte1, 2);
		final long B7 = setByteToLong(byte1, 1);
		final long B8 = setByteToLong(byte1, 0);
		final long result = B1 | B2 | B3 | B4 | B5 | B6 | B7 | B8;
		return result;
	}

	@Override
	public int decomposeInteger(int input_alue, int byte_position) {
		int byte_value = ((input_alue >> (8 * byte_position)) & 0x000000ff);
		return byte_value;
	}

	@Override
	public int decomposeInteger(long input_alue, int byte_position) {
		int byte_value = (int) ((input_alue >> (8 * byte_position)) & 0x00000000000000ffL);
		return byte_value;
	}

	@Override
	public int index(boolean b) {
		if (b) {
			return 1;
		}
		return 0;
	}

	@Override
	public long power(long a, long n) {
		return a ^ n;
	}

	@Override
	public Int2 newInt2() {
		return new RedInt2();
	}

	@Override
	public Int2 newInt2(long cell_x, long cell_y) {
		return new RedInt2(cell_x, cell_y);
	}

	@Override
	public Int2 newInt2(Int2 original, long offset_x, long offset_y) {
		return new RedInt2(original.getX() + offset_x, original.getY()
				+ offset_y);
	}

	@Override
	public long abs(long value) {
		if (value > 0) {
			return value;
		}
		return -value;
	}

}
