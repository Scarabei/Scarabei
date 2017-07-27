
package com.jfixby.scarabei.red.java.gc;

import com.jfixby.scarabei.api.java.gc.BaitInfo;
import com.jfixby.scarabei.api.math.FloatMath;

public class RedBaitInfo implements BaitInfo {

	@Override
	public String toString () {
		return "BaitInfo [bait_id=" + this.bait_id + ", timestamp=" + this.timestamp + ", size=" + this.size() + "]";
	}

	private String size () {
		return FloatMath.roundToDigit(this.size * 1f / (1024 * 1024), 3) + " Mb";
	}

	private final long timestamp;
	private final String bait_id;
	private final long size;
	private final boolean isReinforcable;

	public RedBaitInfo (final RedBait redBait) {
		this.timestamp = redBait.timestamp;
		this.bait_id = redBait.bait_id;
		this.size = redBait.size;
		this.isReinforcable = redBait.isReinforcable;

	}

	@Override
	public boolean isReinforcable () {
		return this.isReinforcable;
	}

	@Override
	public long getSize () {
		return this.size;
	}

}
