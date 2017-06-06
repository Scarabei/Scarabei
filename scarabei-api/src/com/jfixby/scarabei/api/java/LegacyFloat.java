
package com.jfixby.scarabei.api.java;

public class LegacyFloat implements Comparable<LegacyFloat> {

	public float value;

	@Override
	public int compareTo (final LegacyFloat o) {
		return Float.compare(this.value, o.value);
	}

	@Override
	public String toString () {
		return "" + this.value;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(this.value);
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
		final LegacyFloat other = (LegacyFloat)obj;
		if (Float.floatToIntBits(this.value) != Float.floatToIntBits(other.value)) {
			return false;
		}
		return true;
	}

}
