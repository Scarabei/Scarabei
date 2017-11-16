
package com.jfixby.scarabei.red.time;

import com.jfixby.scarabei.api.sys.Sys;
import com.jfixby.scarabei.api.time.ResetableTimeStream;
import com.jfixby.scarabei.api.time.TimeStream;

final public class RedResetableTimeStream implements ResetableTimeStream {

	private TimeStream parent;
	private long start;

	public RedResetableTimeStream (final TimeStream parent) {
		this.parent = parent;
		if (parent == null) {
			this.parent = Sys.SystemTime();
		}
		this.reset();
	}

	@Override
	final public long currentTimeMillis () {
		return this.parent.currentTimeMillis() - this.start;
	}

	@Override
	final public void reset () {
		this.start = this.parent.currentTimeMillis();
	}

}
