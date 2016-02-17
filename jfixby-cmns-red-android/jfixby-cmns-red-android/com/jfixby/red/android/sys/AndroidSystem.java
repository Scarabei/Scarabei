package com.jfixby.red.android.sys;

import com.jfixby.cmns.api.log.L;
import com.jfixby.red.sys.RedSystem;

public class AndroidSystem extends RedSystem {

	@Override
	public void exit() {
		L.d("EXIT");
		System.exit(0);
	}

	@Override
	public boolean sleep(long period) {
		try {
			Thread.sleep(period);
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

}
