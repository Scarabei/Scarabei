
package com.jfixby.scarabei.red.time;

import com.jfixby.scarabei.api.time.ResetableTimeStream;
import com.jfixby.scarabei.api.time.TimeComponent;
import com.jfixby.scarabei.api.time.TimeStream;

public class RedTime implements TimeComponent {

	@Override
	public ResetableTimeStream newResetableTimeStream (final TimeStream systemTime) {
		return new RedResetableTimeStream(systemTime);
	}

}
