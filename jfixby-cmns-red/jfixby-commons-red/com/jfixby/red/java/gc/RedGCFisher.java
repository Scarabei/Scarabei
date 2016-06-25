
package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.java.gc.Bait;
import com.jfixby.cmns.api.java.gc.BaitInfo;
import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.java.gc.GCFisherComponent;
import com.jfixby.cmns.api.log.L;

public class RedGCFisher implements GCFisherComponent {

	static long bait_id = -1;
	private long delayPeriod;

	@Override
	synchronized public BaitInfo throwBait (final long size_in_bytes, final boolean isReinforcable) {
		bait_id++;
		final RedBait bait = new RedBait(bait_id, size_in_bytes, this.delayPeriod, isReinforcable);
		final RedBaitInfo info = bait.getInfo();
		return info;

// final long DEFAULT_BAIT_SIZE = SystemSettings.getLongParameter(GCFisher.DefaultBaitSize);
// return this.throwBait(DEFAULT_BAIT_SIZE);
	}

	long delta;

	@Override
	public void onBaitCaptured (final Bait bait) {

		this.delta = System.currentTimeMillis();

		final String message = "GC bait captured " + bait.getInfo();
		L.e(message);
		// L.e(new Error(message));

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

	private int parse (final String config_line) {
		try {
			final int size = Integer.parseInt(config_line.toLowerCase().replaceAll("mb", ""));
			return size;
		} catch (final Throwable e) {
			Err.reportError("Failed to read GCFisher.DefaultBaitSize: " + config_line);
		}
		return 1;
	}

	@Override
	public void setGCDelay (final long delayPeriod) {
		this.delayPeriod = delayPeriod;
	}

	@Override
	public void forceGC () {
	}

}
