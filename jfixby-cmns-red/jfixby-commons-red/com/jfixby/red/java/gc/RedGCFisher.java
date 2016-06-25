
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

	GCForceSession currentSession;

	@Override
	public void forceGC (final long bait_size_in_bytes) {
		this.currentSession.end();
		this.currentSession = new GCForceSession();
		this.currentSession.begin();
		this.currentSession.throwBait(bait_size_in_bytes, false);
		boolean pushing = true;
		while (pushing) {
			pushing = this.currentSession.push(bait_size_in_bytes, false);
		}

	}

	@Override
	public void onBaitCapturedByGC (final BaitInfo bait) {
		Debug.checkNull("agrument", bait);
		this.currentSession.onCapture(bait);
	}

	@Override
	public BaitInfo throwBait (final long size_in_bytes, final boolean isReinforcable) {
		return this.currentSession.throwBait(size_in_bytes, isReinforcable);
	}

	@Override
	public MemoryStatistics getMemoryStatistics () {
		return new RedMemoryStatistics();
	}

}
