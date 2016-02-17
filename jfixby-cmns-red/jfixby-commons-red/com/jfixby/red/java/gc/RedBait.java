package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.java.gc.Bait;
import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.sys.Sys;

public class RedBait implements Bait {

	public final byte[] weight = new byte[1024 * 1024 * 500];

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		GCFisher.onBaitCaptured(this);
	}

	final long bait_id;
	final long timestamp;
	private RedBaitInfo info;

	public RedBait(long bait_id) {
		this.bait_id = bait_id;
		this.timestamp = Sys.SystemTime().currentTimeMillis();
		this.info = new RedBaitInfo(this);
	}

	public RedBaitInfo getInfo() {
		return info;
	}

}
