package com.jfixby.red.collections;

import com.jfixby.cmns.api.java.IntValue;
import com.jfixby.cmns.api.math.FloatMath;

public class RedHistogrammValue {

    private RedHistogramm master;

    public RedHistogrammValue(RedHistogramm redHistogramm) {
	this.master = redHistogramm;
    }

    @Override
    public String toString() {
	return "" + value + " - " + FloatMath.roundToDigit(100f * master.presence(this.value), 2) + "%";
    }

    public long value;

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
	IntValue other = (IntValue) obj;
	if (value != other.value)
	    return false;
	return true;
    }
}
