package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.java.gc.Bait;
import com.jfixby.cmns.api.java.gc.BaitInfo;
import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.java.gc.GCFisherComponent;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.Sys;

public class RedGCFisher implements GCFisherComponent {

	static long bait_id = -1;

	@Override
	synchronized public BaitInfo throwBait(int size) {
		bait_id++;
		RedBait bait = new RedBait(bait_id, size);
		RedBaitInfo info = bait.getInfo();
		return info;
	}

	@Override
	public void onBaitCaptured(Bait bait) {
		String message = "GC bait captured " + bait.getInfo();
		L.e(message);
//		L.e(new Error(message));
		GCFisher.throwBait();
	}

	@Override
	public BaitInfo throwBait() {

		String config = Sys.getStringParameter(GCFisher.DefaultBaitSize);
		int DEFAULT_BAIT_SIZE = parse(config);
		return this.throwBait(DEFAULT_BAIT_SIZE);
	}

	private int parse(String config_line) {
		try {
			int size = Integer.parseInt(config_line.toLowerCase().replaceAll("mb", ""));
			return size;
		} catch (Throwable e) {
			Err.reportError("Failed to read GCFisher.DefaultBaitSize: " + config_line);
		}
		return 1;
	}

}
