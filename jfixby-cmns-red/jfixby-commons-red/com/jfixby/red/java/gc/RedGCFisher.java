package com.jfixby.red.java.gc;

import com.jfixby.cmns.api.java.gc.Bait;
import com.jfixby.cmns.api.java.gc.BaitInfo;
import com.jfixby.cmns.api.java.gc.GCFisher;
import com.jfixby.cmns.api.java.gc.GCFisherComponent;
import com.jfixby.cmns.api.log.L;

public class RedGCFisher implements GCFisherComponent {

	static long bait_id = -1;

	@Override
	synchronized public BaitInfo throwBait() {
		bait_id++;
		RedBait bait = new RedBait(bait_id);
		RedBaitInfo info = bait.getInfo();
		return info;
	}

	@Override
	public void onBaitCaptured(Bait bait) {
		String message = "GC bait captured " + bait.getInfo();
		L.d(message);
		new Error(message).printStackTrace();
		GCFisher.throwBait();
	}

}
