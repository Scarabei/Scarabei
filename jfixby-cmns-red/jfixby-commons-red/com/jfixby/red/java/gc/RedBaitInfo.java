package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.java.gc.BaitInfo;

public class RedBaitInfo implements BaitInfo {

	@Override
	public String toString() {
		return "RedBaitInfo [bait_id=" + bait_id + ", timestamp=" + timestamp + "]";
	}

	private long timestamp;
	private long bait_id;

	public RedBaitInfo(RedBait redBait) {
		this.timestamp = redBait.timestamp;
		this.bait_id = redBait.bait_id;
	}

}
