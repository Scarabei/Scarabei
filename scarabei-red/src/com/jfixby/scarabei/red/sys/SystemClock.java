
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.time.TimeStream;

public class SystemClock implements TimeStream {

	@Override
	public String toString () {
		return "SystemTime[" + this.currentTimeMillis() + "]";
	}

	@Override
	public long currentTimeMillis () {
// final String msg = L.stackTraceToString(new Error());
// if (msg.contains("com.jfixby.tinto.ui.game.TintoGameClock.currentTimeMillis")
// || msg.contains("com.jfixby.r3.engine.core.unit.anim.SimpleAnimator.onUpdate")) {
//
// } else {
// Debug.printCallStack();
// }

		return System.currentTimeMillis();
	}

}
