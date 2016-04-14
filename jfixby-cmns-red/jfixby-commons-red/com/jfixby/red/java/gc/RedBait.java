
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
	private RedBaitInfo info;
	int BAIT_SIZE;

	public RedBait (long bait_id, long size_in_bytes) {
		this.bait_id = bait_id;
		this.timestamp = Sys.SystemTime().currentTimeMillis();
		this.BAIT_SIZE = (int)size_in_bytes;
		if (BAIT_SIZE < 0) {
			BAIT_SIZE = Integer.MAX_VALUE;
		}
		this.info = new RedBaitInfo(this);
		weight = new byte[BAIT_SIZE];
	}

	public RedBaitInfo getInfo () {
		return info;
	}

}
