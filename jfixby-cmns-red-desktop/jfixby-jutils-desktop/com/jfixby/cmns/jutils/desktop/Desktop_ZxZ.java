package com.jfixby.cmns.jutils.desktop;

public class Desktop_ZxZ {

	public Desktop_ZxZ(long x_index, long y_index) {
		super();
		this.x_index = x_index;
		this.y_index = y_index;
	}

	@Override
	public String toString() {
		return "IndexedPosition [" + x_index + ", " + y_index + "]";
	}

	final long x_index;
	final long y_index;

	
	public long x_index() {
		return this.x_index;
	}

	
	public long y_index() {
		return this.y_index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (x_index ^ (x_index >>> 32));
		result = prime * result + (int) (y_index ^ (y_index >>> 32));
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
		Desktop_ZxZ other = (Desktop_ZxZ) obj;
		if (x_index != other.x_index)
			return false;
		if (y_index != other.y_index)
			return false;
		return true;
	}

	public static Desktop_ZxZ newIndexedPosition(long x, long y) {
		return new Desktop_ZxZ(x, y);
	}

	public static Desktop_ZxZ newIndexedPosition(Desktop_ZxZ old, long x, long y) {
		return new Desktop_ZxZ(old.x_index() + x, old.y_index() + y);
	}

}
