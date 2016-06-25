
package com.jfixby.cmns.api.java.gc;

public interface GCFisherComponent {

	public void onBaitCaptured (Bait bait);

	public void setGCDelay (long delayPeriod);

	public void forceGC ();

	public BaitInfo throwBait (long size_in_bytes, boolean isReinforcable);

// public BaitInfo throwBait (boolean reinforcable);
//
// public BaitInfo throwBait (long size_in_bytes);
//

}
