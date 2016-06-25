
package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.java.gc.Bait;
import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.sys.Sys;

public class RedBait implements Bait {

	public final byte[] weight;

	@Override
	protected void finalize () throws Throwable {

		super.finalize();
		GCFisher.onBaitCaptured(this.info);

	}

	final long bait_id;
	final long timestamp;
	private final RedBaitInfo info;
	long size;
	private final long delayPeriod;
	final boolean isReinforcable;

	public RedBait (final long bait_id, final long size_in_bytes, final long delayPeriod, final boolean isReinforcable) {
		this.bait_id = bait_id;
		this.isReinforcable = isReinforcable;
		this.delayPeriod = delayPeriod;
		this.timestamp = Sys.SystemTime().currentTimeMillis();
		this.size = (int)size_in_bytes;
		if (this.size < 0) {
			this.size = Integer.MAX_VALUE - 1;
		}
		this.info = new RedBaitInfo(this);
		this.weight = new byte[(int)this.size];
	}

	@Override
	public RedBaitInfo getInfo () {
		return this.info;
	}

}
