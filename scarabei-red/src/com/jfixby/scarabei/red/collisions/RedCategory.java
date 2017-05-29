
package com.jfixby.scarabei.red.collisions;

import com.jfixby.scarabei.api.collisions.AtomicCollisionCategory;

public class RedCategory implements AtomicCollisionCategory {

	private static final RedCategory ALL = new RedCategory(-1);
	private static final RedCategory NONE = new RedCategory(0);

	// private final static short DEFAULT_CATEGORY = (short) 0xffff;

	// private static final CollisionCategory DEFAULT =

	public static RedCategory ALL () {
		return ALL;
	}

	public static RedCategory NONE () {
		return NONE;
	}

	final long bits;
	final String string_represantation;
	private RedAtomicCategoriesList owner;

	public RedCategory (final long category_id) {
		this.bits = category_id;
		this.string_represantation = longToBitsString(this.bits);
	}

// public static final String shortToBitsString (final short bits) {
// String binarized = Integer.toBinaryString(bits);
// final int len = binarized.length();
// final String sixteenZeroes = "00000000000000000";
// if (len < 16) {
// binarized = sixteenZeroes.substring(0, 16 - len).concat(binarized);
// } else {
// binarized = binarized.substring(len - 16);
// }
// return binarized;
// }
	static final String sixteenZeroes = "00000000000000000";
	static final String sixtyfourZeroes = sixteenZeroes + sixteenZeroes + sixteenZeroes + sixteenZeroes;

	public static final String longToBitsString (final long bits) {
		String binarized = Long.toBinaryString(bits);
		final int len = binarized.length();

		final int size = 64;
		if (len < size) {
			binarized = sixtyfourZeroes.substring(0, size - len).concat(binarized);
		} else {
			binarized = binarized.substring(len - size);
		}
		return binarized;
	}

	public RedCategory (final int index, final RedAtomicCategoriesList redAtomicCategoriesList) {
		this.bits = index_to_binary(index);
		this.string_represantation = longToBitsString(this.bits);
		this.owner = redAtomicCategoriesList;
	}

	static private long index_to_binary (final int index) {
		final long result = (pow(2, index));
		return result;
	}

	private static long pow (final long A, final long B) {
		long result = 1;
		for (int i = 0; i < B; i++) {
			result = result * A;
		}
		return result;
	}

	public static RedCategory NOT (final RedCategory a) {
		return new RedCategory(~a.bits);
	}

	public static RedCategory OR (final RedCategory a, final RedCategory b) {
		return new RedCategory(a.bits | b.bits);
	}

	public static RedCategory AND (final RedCategory a, final RedCategory b) {
		return new RedCategory(a.bits & b.bits);
	}

	@Override
	public String toString () {
		return "CollisionCategory [" + this.string_represantation + "]";
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = (int)(prime * result + this.bits);
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RedCategory other = (RedCategory)obj;
		if (this.bits != other.bits) {
			return false;
		}
		return true;
	}

	@Override
	public long getBits () {
		return this.bits;
	}
}
