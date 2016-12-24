
package com.jfixby.scarabei.red.java.gc;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.java.gc.BaitInfo;
import com.jfixby.scarabei.api.java.gc.GARBAGE_MODE;
import com.jfixby.scarabei.api.java.gc.GCFisherComponent;
import com.jfixby.scarabei.api.java.gc.MemoryStatistics;
import com.jfixby.scarabei.api.log.L;

public class RedGCFisher implements GCFisherComponent {

	public RedGCFisher () {
		super();
		this.currentSession = new GCForceSession();
		this.currentSession.begin();

	}

	GCForceSession currentSession;
	private GARBAGE_MODE mode = GARBAGE_MODE.REGULAR;

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
		L.d("GC");

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

	@Override
	public boolean isGarbageModeFlag (final GARBAGE_MODE mode) {
		return this.mode == mode;
	}

	@Override
	public GARBAGE_MODE getGarbageModeFlag () {
		return this.mode;
	}

	@Override
	public void setGarbageModeFlag (final GARBAGE_MODE mode) {
		Debug.checkNull("GARBAGE_PRODUCTION_MODE", mode);
		this.mode = mode;
	}

}
