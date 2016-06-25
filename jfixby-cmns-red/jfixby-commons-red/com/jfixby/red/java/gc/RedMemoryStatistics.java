
package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.java.gc.MemoryStatistics;
import com.jfixby.cmns.api.java.gc.MemoryStatisticsComparison;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.math.FloatMath;

public class RedMemoryStatistics implements MemoryStatistics {

	private final long timestamp;
	private final long totalMemory;
	private final long maxMemory;
	private final long freeMemory;
	private final long usedMemory;

	public RedMemoryStatistics () {
		this.timestamp = System.currentTimeMillis();
		// Getting the runtime reference from system
		final Runtime runtime = Runtime.getRuntime();
		this.totalMemory = runtime.totalMemory();
		this.maxMemory = runtime.maxMemory();
		this.freeMemory = runtime.freeMemory();
		this.usedMemory = this.totalMemory - this.freeMemory;

	}

	@Override
	public void print (final String tag) {
		L.d("MemoryStatistics[" + tag + "]");
		L.d("     ",
			printMem("Total", this.totalMemory) + ", " + //
				printMem("Used", this.usedMemory) + ", " + //
				printMem("Free", this.freeMemory) + ", " + //
				printMem("Max", this.maxMemory));
	}

	static private String printMem (final String tag, final long size) {
		return tag + ": " + FloatMath.roundToDigit(size * 1f / GCFisher.MB, 2) + "Mb";
	}

	@Override
	public MemoryStatisticsComparison compareTo (final MemoryStatistics other) {
		return new RedMemoryStatisticsComparison(other, this);
	}

	@Override
	public long getTotalMemory () {
		return this.totalMemory;
	}

	@Override
	public long getUsedMemory () {
		return this.usedMemory;
	}

	@Override
	public long getFreeMemory () {
		return this.freeMemory;
	}

	@Override
	public long getMaxMemory () {
		return this.maxMemory;
	}

}
