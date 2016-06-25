
package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.java.gc.BaitInfo;
import com.jfixby.cmns.api.java.gc.GCFisherComponent;
import com.jfixby.cmns.api.java.gc.MemoryStatistics;

public class RedGCFisher implements GCFisherComponent {

	public RedGCFisher () {
		super();
		this.currentSession = new GCForceSession();
		this.currentSession.begin();

	}

	GCForceSession currentSession = null;

	@Override
	public void forceGC (final long bait_size_in_bytes) {
		while (this.currentSession.isActive()) {
			this.currentSession.push(bait_size_in_bytes, false);
		}
		this.currentSession = new GCForceSession();
		this.currentSession.begin();
	}

	@Override
	public void onBaitCapturedByGC (final BaitInfo bait) {
		Debug.checkNull("agrument", bait);

		this.currentSession.onCapture(bait);

	}

	@Override
	public BaitInfo throwBait (final long size_in_bytes, final boolean isReinforcable) {
		return this.currentSession.throwBait(size_in_bytes, isReinforcable);

// final long DEFAULT_BAIT_SIZE = SystemSettings.getLongParameter(GCFisher.DefaultBaitSize);
// return this.throwBait(DEFAULT_BAIT_SIZE);
	}

	@Override
	public MemoryStatistics getMemoryStatistics () {
		return new RedMemoryStatistics();
	}

}
