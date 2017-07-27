
package com.jfixby.scarabei.red.math;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.math.Int2;
import com.jfixby.scarabei.api.math.IntegerMathComponent;

public class RedIntegerMath implements IntegerMathComponent {

	@Override
	public long limit (final long left_border, final long value, final long right_border) {
		if (left_border > right_border) {
			return this.limit(right_border, value, left_border);
		}
		if (value < left_border) {
			return left_border;
		}
		if (value > right_border) {
			return right_border;
		}
		return value;

	}

	@Override
	public final long ZtoN (final long z_number) {
		if (z_number < 0) {
			return -z_number * 2 - 1;
		} else {
			return z_number * 2;
		}
	}

	@Override
	public final long NtoZ (final long n_number) {
		checkIfItIsNatural(n_number);
		if (n_number % 2 == 0) {// positive z
			return n_number / 2;
		} else {
			return -(n_number + 1) / 2;
		}
	}

	private static final void checkIfItIsNatural (final long n_number) {
		if (n_number < 0) {
			Err.reportError("This is not a natural number:" + n_number);
		}
	}

	@Override
	public final long ZxZtoN (final long z_x, final long z_y) {
		return this.NxNtoN(this.ZtoN(z_x), this.ZtoN(z_y));
	}

	@Override
	public final long NtoZxZgetX (final long number) {
		checkIfItIsNatural(number);
		final long n_x = this.NtoNxNgetX(number);
		// final long n_y = NtoNxNgetY(number);
		final long z_x = this.NtoZ(n_x);
		// final long z_y = NtoZ(n_y);
		return z_x;
	}

	@Override
	public final long NtoZxZgetY (final long number) {
		checkIfItIsNatural(number);
		// final long n_x = NtoNxNgetX(number);
		final long n_y = this.NtoNxNgetY(number);
		// final long z_x = NtoZ(n_x);
		final long z_y = this.NtoZ(n_y);
		return z_y;
	}

	@Override
	public final long NxNtoN (final long n_x, final long n_y) {
		checkIfItIsNatural(n_x);
		checkIfItIsNatural(n_y);
		final long c = n_x + n_y + 1;
		final long a = c * (c - 1) / 2;
		final long num_x_y = a + n_y;
		return num_x_y;
	}

	@Override
	public final long NtoNxNgetY (final long number_xy) {
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

	@Override
	public final long NtoNxNgetX (final long number_xy) {
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

	@Override
	public long min (final long a, final long b) {
		if (a < b) {
			return a;
		}
		return b;
	}

	@Override
	public long max (final long a, final long b) {
		if (a < b) {
			return b;
		}
		return a;
	}

	@Override
	public int composeInteger (final byte byte1, final byte byte2, final byte byte3, final byte byte4) {
		final int B1 = this.setByteToInt(byte1, 3);
		final int B2 = this.setByteToInt(byte1, 2);
		final int B3 = this.setByteToInt(byte1, 1);
		final int B4 = this.setByteToInt(byte1, 0);
		final int result = B1 | B2 | B3 | B4;
		return result;
	}

	private int setByteToInt (final byte byte_value, final int byte_position) {
		final int result = (((byte_value) << (8 * byte_position)) & int_mask(byte_position));
		return result;
	}

	private long setByteToLong (final byte byte_value, final int byte_position) {
		final long result = (((byte_value) << (8 * byte_position)) & long_mask(byte_position));
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

	static final private int int_mask (final int byte_position) {
		return INT_MASKS[byte_position];
	}

	static final private long long_mask (final int byte_position) {
		return LONG_MASKS[byte_position];
	}

	@Override
	public long composeInteger (final byte byte1, final byte byte2, final byte byte3, final byte byte4, final byte byte5,
		final byte byte6, final byte byte7, final byte byte8) {
		final long B1 = this.setByteToLong(byte1, 7);
		final long B2 = this.setByteToLong(byte1, 6);
		final long B3 = this.setByteToLong(byte1, 5);
		final long B4 = this.setByteToLong(byte1, 4);
		final long B5 = this.setByteToLong(byte1, 3);
		final long B6 = this.setByteToLong(byte1, 2);
		final long B7 = this.setByteToLong(byte1, 1);
		final long B8 = this.setByteToLong(byte1, 0);
		final long result = B1 | B2 | B3 | B4 | B5 | B6 | B7 | B8;
		return result;
	}

	@Override
	public int decomposeInteger (final int input_alue, final int byte_position) {
		final int byte_value = ((input_alue >> (8 * byte_position)) & 0x000000ff);
		return byte_value;
	}

	@Override
	public int decomposeInteger (final long input_alue, final int byte_position) {
		final int byte_value = (int)((input_alue >> (8 * byte_position)) & 0x00000000000000ffL);
		return byte_value;
	}

	@Override
	public int index (final boolean b) {
		if (b) {
			return 1;
		}
		return 0;
	}

	@Override
	public long power (final long a, final int n) {
		return FloatMath.round(StrictMath.pow(a, n));
	}

	@Override
	public Int2 newInt2 () {
		return new RedInt2();
	}

	@Override
	public Int2 newInt2 (final long cell_x, final long cell_y) {
		return new RedInt2(cell_x, cell_y);
	}

	@Override
	public Int2 newInt2 (final Int2 original, final long offset_x, final long offset_y) {
		return new RedInt2(original.getX() + offset_x, original.getY() + offset_y);
	}

	@Override
	public long abs (final long value) {
		if (value > 0) {
			return value;
		}
		return -value;
	}

	@Override
	public boolean isPowerOfTwo (final long x) {
		return x > 0 && (x & x - 1) == 0;
	}

	@Override
	public long rightPowerOfTwo (final long value) {
		if (value < 0) {
			Err.reportError("value=" + value + " is negative");
		}
		if (value == 0) {
			return 0;
		}
		if (value == 1) {
			return 1;
		}

		if (this.isPowerOfTwo(value)) {
			return (long)(FloatMath.component().log(2, value)) + 1;
		}
		return FloatMath.component().floorUp(FloatMath.component().log(2, value));
	}

	@Override
	final public long powerOfTwo (final int power) {
		long result = 1;
		for (int i = 0; i < power; i++) {
			result = result << 1;
		}
		return result;
	}

	@Override
	public int compare (final long a, final long b) {
		return Long.valueOf(a).compareTo(Long.valueOf(b));
	}

}
