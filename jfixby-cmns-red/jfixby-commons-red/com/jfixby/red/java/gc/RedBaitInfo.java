
package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.java.gc.BaitInfo;
import com.jfixby.cmns.api.math.FloatMath;

public class RedBaitInfo implements BaitInfo {

	@Override
	public String toString () {
		return "BaitInfo [bait_id=" + this.bait_id + ", timestamp=" + this.timestamp + ", size=" + this.size() + "]";
	}

	private String size () {
		return FloatMath.roundToDigit(this.size * 1f / (1024 * 1024), 3) + " Mb";
	}

	private final long timestamp;
	private final long bait_id;
	private final int size;

	public RedBaitInfo (final RedBait redBait) {
		this.timestamp = redBait.timestamp;
		this.bait_id = redBait.bait_id;
		this.size = redBait.BAIT_SIZE;
	}

}
