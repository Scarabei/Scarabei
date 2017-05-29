
package com.jfixby.scarabei.red.collisions;

public class RedFilterMask {
	private static final long DEFAULT_MASK = -1;
	long bits;
	String string_represantation;

	public RedFilterMask (final long category_id) {
		this.bits = category_id;
		this.string_represantation = RedCategory.longToBitsString(category_id);
	}

	public RedFilterMask () {
		this(DEFAULT_MASK);
	}

	public long getBits () {
		return this.bits;
	}

	public void setBits (final long bits) {
		this.bits = bits;
		this.string_represantation = RedCategory.longToBitsString(bits);
	}

	@Override
	public String toString () {
		return "CollisionFilterMask [" + this.string_represantation + "]";
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
		final RedFilterMask other = (RedFilterMask)obj;
		if (this.bits != other.bits) {
			return false;
		}
		return true;
	}

	public void rejectCollisionsWith (final RedCategory category) {
		final long category_bits = category.getBits();
		final long current_mask_bits = this.bits;
		this.setBits((current_mask_bits) & (~category_bits));

	}

	public void acceptCollisionsWith (final RedCategory category) {
		final long category_bits = category.getBits();
		final long current_mask_bits = this.bits;
		this.setBits(current_mask_bits | category_bits);

	}
}
