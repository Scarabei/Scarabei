package com.jfixby.cmns.api.lang;

public class Int implements Comparable<Int> {

	public long value;

	public Int(long value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value + "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (value ^ (value >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Int other = (Int) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public int compareTo(Int o) {
		return Long.compare(value, o.value);
	}

}
