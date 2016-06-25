
package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.java.gc.MemoryStatistics;
import com.jfixby.cmns.api.java.gc.MemoryStatisticsComparison;
import com.jfixby.red.desktop.DesktopSetup;

public class GCFisherTest {

	public static void main (final String[] args) {
		DesktopSetup.deploy();
		final MemoryStatistics before = GCFisher.getMemoryStatistics();
		before.print("before");

		{
// final Object o = new byte[1024 * 1024];
		}
		GCFisher.forceGC(1);

		final MemoryStatistics after = GCFisher.getMemoryStatistics();
		after.print("after");

		final MemoryStatisticsComparison compare = after.compareTo(before);

		compare.print("compare");
	}

}
