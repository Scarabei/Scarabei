
package com.jfixby.scarabei.api.java;

public class LegacyInt implements Comparable<LegacyInt> {

	public int value;

	@Override
	public int compareTo (final LegacyInt o) {
		return Integer.compare(this.value, o.value);
	}

	@Override
	public String toString () {
		return "" + this.value;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.value;
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
		final LegacyInt other = (LegacyInt)obj;
		if (this.value != other.value) {
			return false;
		}
		return true;
	}

}
