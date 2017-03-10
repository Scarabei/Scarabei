
package com.jfixby.scarabei.api.math;

public interface IntegerMathComponent {

	public long limit (long left_border, long value, long right_border);

	public long ZtoN (long z_number);

	public long NtoZ (long n_number);

	public long ZxZtoN (long z_x, long z_y);

	public long NtoZxZgetX (long number);

	public long NtoZxZgetY (long number);

	public long NxNtoN (long n_x, long n_y);

	public long NtoNxNgetY (long number_xy);

	public long NtoNxNgetX (long number_xy);

	public long min (long a, long b);

	public long max (long a, long b);

	public int composeInteger (byte byte1, byte byte2, byte byte3, byte byte4);

	public long composeInteger (byte byte1, byte byte2, byte byte3, byte byte4, byte byte5, byte byte6, byte byte7, byte byte8);

	public int decomposeInteger (int input_alue, int byte_position);

	public int decomposeInteger (long input_alue, int byte_position);

	public int index (boolean b);

	public long power (long a, int n);

	public Int2 newInt2 ();

	public Int2 newInt2 (long cell_x, long cell_y);

	public Int2 newInt2 (Int2 original, long offset_x, long offset_y);

	public long abs (long value);

	public boolean isPowerOfTwo (long x);

	public long rightPowerOfTwo (long size);

	public long powerOfTwo (int power);

	public int compare (long a, long b);

}
