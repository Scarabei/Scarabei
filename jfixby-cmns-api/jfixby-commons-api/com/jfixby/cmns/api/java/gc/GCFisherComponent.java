package com.jfixby.cmns.api.java.gc;

public interface GCFisherComponent {

	public BaitInfo throwBait(int size_in_MB);

	public BaitInfo throwBait();

	public void onBaitCaptured(Bait bait);

}
