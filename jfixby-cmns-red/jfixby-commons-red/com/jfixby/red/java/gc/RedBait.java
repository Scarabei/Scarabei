
package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.java.gc.Bait;
import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.sys.Sys;

public class RedBait implements Bait {

	public final byte[] weight;

	@Override
	protected void finalize () throws Throwable {

		super.finalize();
		GCFisher.onBaitCaptured(this);

	}

	final long bait_id;
	final long timestamp;
	private final RedBaitInfo info;
	int BAIT_SIZE;
	private final long delayPeriod;

	public RedBait (final long bait_id, final long size_in_bytes, final long delayPeriod) {
		this.bait_id = bait_id;
		this.delayPeriod = delayPeriod;
		this.timestamp = Sys.SystemTime().currentTimeMillis();
		this.BAIT_SIZE = (int)size_in_bytes;
		if (this.BAIT_SIZE < 0) {
			this.BAIT_SIZE = Integer.MAX_VALUE;
		}
		this.info = new RedBaitInfo(this);
		this.weight = new byte[this.BAIT_SIZE];
	}

	@Override
	public RedBaitInfo getInfo () {
		return this.info;
	}

}
