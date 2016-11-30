
package com.jfixby.cmns.api.java;

public class Int {

	public Int () {
		this(0L);
	}

	public Int (final long l) {
		this.value = l;
	}

	@Override
	public String toString () {
		return "" + this.value;
	}

	public long value;

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)(this.value ^ (this.value >>> 32));
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
		final Int other = (Int)obj;
		if (this.value != other.value) {
			return false;
		}
		return true;
	}

}
