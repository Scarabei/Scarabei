
package com.jfixby.cmns.api.java.gc;

public interface GCFisherComponent {

	public void onBaitCaptured (BaitInfo bait);

	public void setGCDelay (long delayPeriod);

	public void forceGC (long bait_size_in_bytes);

	public BaitInfo throwBait (long size_in_bytes, boolean isReinforcable);

	public MemoryStatistics getMemoryStatistics ();

// public BaitInfo throwBait (boolean reinforcable);
//
// public BaitInfo throwBait (long size_in_bytes);
//

}
