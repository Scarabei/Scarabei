
package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.java.gc.MemoryStatistics;
import com.jfixby.red.desktop.DesktopSetup;

public class GCFisherTest {

	public static void main (final String[] args) {
		DesktopSetup.deploy();

		final MemoryStatistics before = GCFisher.getMemoryStatistics();

		before.print("before");
		final MemoryStatistics after = GCFisher.getMemoryStatistics();

		after.print("after");
	}

}
