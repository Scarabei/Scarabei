
package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.java.gc.BaitInfo;
import com.jfixby.cmns.api.math.FloatMath;

public class RedBaitInfo implements BaitInfo {

	@Override
	public String toString () {
		return "RedBaitInfo [bait_id=" + bait_id + ", timestamp=" + timestamp + ", size=" + size() + "]";
	}

	private String size () {
		return FloatMath.roundToDigit(size * 1f / (1024 * 1024), 3) + " Mb";
	}

	private long timestamp;
	private long bait_id;
	private int size;

	public RedBaitInfo (RedBait redBait) {
		this.timestamp = redBait.timestamp;
		this.bait_id = redBait.bait_id;
		this.size = redBait.BAIT_SIZE;
	}

}
