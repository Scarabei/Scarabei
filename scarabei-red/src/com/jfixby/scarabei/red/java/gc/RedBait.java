
package com.jfixby.scarabei.red.java.gc;

import com.jfixby.scarabei.api.java.gc.Bait;
import com.jfixby.scarabei.api.java.gc.GCFisher;
import com.jfixby.scarabei.api.sys.Sys;

public class RedBait implements Bait {

	public final byte[] weight;

	@Override
	protected void finalize () throws Throwable {
		super.finalize();
		GCFisher.invoke().onBaitCapturedByGC(this.info);
	}

	final String bait_id;
	final long timestamp;
	private final RedBaitInfo info;
	long size;
	final boolean isReinforcable;

	public RedBait (final String bait_id, final long size_in_bytes, final boolean isReinforcable) {
		this.bait_id = bait_id;
		this.isReinforcable = isReinforcable;
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
