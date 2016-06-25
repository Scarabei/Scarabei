
package com.jfixby.cmns.api.java.gc;

public interface MemoryStatistics {

	void print (String tag);

	MemoryStatisticsComparison compareTo (MemoryStatistics other);

	long getTotalMemory ();

	long getUsedMemory ();

	long getFreeMemory ();

	long getMaxMemory ();

}
