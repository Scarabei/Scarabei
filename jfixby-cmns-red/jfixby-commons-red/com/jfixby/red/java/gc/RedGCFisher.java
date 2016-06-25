
package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.java.gc.BaitInfo;
import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.java.gc.GCFisherComponent;
import com.jfixby.cmns.api.java.gc.MemoryStatistics;
import com.jfixby.cmns.api.log.L;

public class RedGCFisher implements GCFisherComponent {

	static long bait_id = -1;
	private long delayPeriod;

	long delta;
	private boolean IS_WAITING_FOR_GC;

	@Override
	public void setGCDelay (final long delayPeriod) {
		this.delayPeriod = delayPeriod;
	}

	@Override
	public void forceGC (final long bait_size_in_bytes) {
		this.IS_WAITING_FOR_GC = true;
		while (this.IS_WAITING_FOR_GC) {
			this.throwBait(bait_size_in_bytes, false);
		}
	}

	@Override
	public void onBaitCaptured (final BaitInfo bait) {
		Debug.checkNull("agrument", bait);
		this.IS_WAITING_FOR_GC = false;
		this.delta = System.currentTimeMillis();
		final String message = "GC bait captured " + bait;
		L.e(message);

		this.delta = System.currentTimeMillis() - this.delta;
		this.delta = this.delayPeriod - this.delta;
		if (this.delta > 0) {
			L.e("GC sleeping", this.delta);
			try {
				Thread.sleep(this.delta);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (bait.isReinforcable()) {
			GCFisher.throwBait(bait.getSize(), bait.isReinforcable());
		}
	}

	@Override
	synchronized public BaitInfo throwBait (final long size_in_bytes, final boolean isReinforcable) {
		bait_id++;
		final RedBait bait = new RedBait(bait_id, size_in_bytes, this.delayPeriod, isReinforcable);
		final RedBaitInfo info = bait.getInfo();
		return info;

// final long DEFAULT_BAIT_SIZE = SystemSettings.getLongParameter(GCFisher.DefaultBaitSize);
// return this.throwBait(DEFAULT_BAIT_SIZE);
	}

	@Override
	public MemoryStatistics getMemoryStatistics () {
		return new RedMemoryStatistics();
	}

}
