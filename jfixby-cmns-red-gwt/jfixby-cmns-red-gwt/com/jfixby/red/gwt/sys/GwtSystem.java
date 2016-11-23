
package com.jfixby.red.gwt.sys;

import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.SystemInfo;
import com.jfixby.red.sys.RedSystem;

public class GwtSystem extends RedSystem {

	@Override
	public void exit () {
		L.d("EXIT");
		System.exit(0);
	}

	@Override
	public boolean sleep (final long period) {
		try {
			Thread.sleep(period);
			return true;
		} catch (final InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public SystemInfo getSystemInfo () {
		Err.reportNotImplementedYet();
		return null;
	}

}
