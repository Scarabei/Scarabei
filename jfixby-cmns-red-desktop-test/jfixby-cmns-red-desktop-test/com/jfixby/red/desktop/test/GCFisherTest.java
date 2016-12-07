
package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.java.gc.MemoryStatistics;
import com.jfixby.cmns.api.java.gc.MemoryStatisticsComparison;

public class GCFisherTest {

	public static void main (final String[] args) {
		DesktopSetup.deploy();
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
