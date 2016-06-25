
package com.jfixby.cmns.api.java.gc;

public interface GCFisherComponent {

	public void onBaitCapturedByGC (BaitInfo bait);// never call this method

	public void forceGC (long bait_size_in_bytes);

	public BaitInfo throwBait (long size_in_bytes, boolean isReinforcable);

	public MemoryStatistics getMemoryStatistics ();

}
