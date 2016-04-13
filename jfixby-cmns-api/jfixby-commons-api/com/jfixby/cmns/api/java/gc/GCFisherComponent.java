
package com.jfixby.cmns.api.java.gc;

public interface GCFisherComponent {

	public BaitInfo throwBait (long size_in_bytes);

	public BaitInfo throwBait ();

	public void onBaitCaptured (Bait bait);

}
