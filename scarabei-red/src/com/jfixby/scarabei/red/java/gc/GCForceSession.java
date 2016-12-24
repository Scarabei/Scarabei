
package com.jfixby.scarabei.red.java.gc;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.java.gc.BaitInfo;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.StateSwitcher;

public class GCForceSession {
	long session_id;

	private final long timestamp;

	public GCForceSession () {
		super();
		this.timestamp = System.currentTimeMillis();
		this.session_id = this.ID();
	}

	static long freeSessionID = -1;

	private long ID () {
		freeSessionID++;
		return freeSessionID;
	}

	public StateSwitcher<GCForceSessionState> state = JUtils.newStateSwitcher(GCForceSessionState.NEW);
	final List<RedBaitInfo> activeBaits = Collections.newList();

	private long thrown;
	private long collected;

	public void begin () {
		this.state.expectState(GCForceSessionState.NEW);
		this.state.switchState(GCForceSessionState.ACTIVE);
// L.d("Open GC-session", this.session_id);
	}

	long lastCapture = -1;

	private boolean GCCallDetected = false;

	public void onCapture (final BaitInfo bait) {
		this.GCCallDetected = true;
		if (!this.activeBaits.contains(bait)) {
			L.e("Alien bait", bait);
			return;
		}
		this.activeBaits.remove(bait);
		this.collected++;
		this.lastCapture = System.currentTimeMillis();
		final String message = "[CAPTURE] " + bait + " Left: " + this.onAir();
// L.d("", message);

	}

	private long onAir () {
		return this.activeBaits.size();
	}

	long free_bait_id = -1;

	public boolean push (final long size_in_bytes, final boolean isReinforcable) {
		this.state.expectState(GCForceSessionState.ACTIVE);

		if (this.GCCallDetected) {
			return false;
		} else {
			this.throwBait(size_in_bytes, isReinforcable);
			return true;
		}
	}

	final RedBaitInfo throwBait (final long size_in_bytes, final boolean isReinforcable) {
		// final long DEFAULT_BAIT_SIZE = SystemSettings.getLongParameter(GCFisher.DefaultBaitSize);
		// return this.throwBait(DEFAULT_BAIT_SIZE);

		this.state.expectState(GCForceSessionState.ACTIVE);
		this.free_bait_id++;
		final RedBait bait = new RedBait(this.session_id + ":" + this.free_bait_id, size_in_bytes, isReinforcable);
		final RedBaitInfo info = bait.getInfo();
		this.thrown++;
		this.activeBaits.add(info);

		final String message = "[THROW] " + info + " On Air: " + this.onAir();
// L.d("", message);

		return info;

	}

	public void end () {
		this.state.expectState(GCForceSessionState.ACTIVE);
		this.state.switchState(GCForceSessionState.CLOSED);
// L.d("Close GC-session", this.session_id);
	}

}
