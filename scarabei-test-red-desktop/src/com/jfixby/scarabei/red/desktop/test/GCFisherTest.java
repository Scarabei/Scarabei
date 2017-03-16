
package com.jfixby.scarabei.red.desktop.test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.java.gc.GCFisher;
import com.jfixby.scarabei.api.java.gc.MemoryStatistics;
import com.jfixby.scarabei.api.java.gc.MemoryStatisticsComparison;

public class GCFisherTest {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();
		final MemoryStatistics before = GCFisher.getMemoryStatistics();
		before.print("before");
		for (int i = 0; i < 10; i++) {
			final Object o = new byte[1024 * 1024];
		}
		GCFisher.forceGC(GCFisher.MB / 10);

		final MemoryStatistics after = GCFisher.getMemoryStatistics();
		after.print("after");

		final MemoryStatisticsComparison compare = after.compareTo(before);

		compare.print("compare");
	}

}
