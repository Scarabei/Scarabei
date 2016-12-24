
package com.jfixby.scarabei.red.java.gc;

import com.jfixby.scarabei.api.java.gc.GCFisher;
import com.jfixby.scarabei.api.java.gc.MemoryStatistics;
import com.jfixby.scarabei.api.java.gc.MemoryStatisticsComparison;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;

public class RedMemoryStatisticsComparison implements MemoryStatisticsComparison {

	private final MemoryStatistics one;
	private final MemoryStatistics other;
	private final long total;
	private final long used;
	private final long free;
	private final long max;

	public RedMemoryStatisticsComparison (final MemoryStatistics one, final MemoryStatistics other) {
		this.one = one;
		this.other = other;
		this.total = this.other.getTotalMemory() - this.one.getTotalMemory();
		this.used = this.other.getUsedMemory() - this.one.getUsedMemory();
		this.free = this.other.getFreeMemory() - this.one.getFreeMemory();
		this.max = this.other.getMaxMemory() - this.one.getMaxMemory();

	}

	@Override
	public void print (final String tag) {
		L.d("MemoryStatisticsComparison[" + tag + "]");
		L.d("     ",
			printMem("Total Memory", this.total) + ", " + //
				printMem("Used", this.used) + ", " + //
				printMem("Free", this.free) + ", " + //
				printMem("Max", this.max));

	}

	static private String printMem (final String tag, final long size) {
		return tag + ": " + sign(size) + FloatMath.roundToDigit(size * 1f / GCFisher.MB, 2) + "Mb";
	}

	private static String sign (final long size) {
		if (size > 0) {
			return "+";
		}
		if (size < 0) {
			return "";
		}
		// ↑↓
		return " ";
	}

	@Override
	public long getUsedMemorySizeChange () {
		return this.used;
	}

}
