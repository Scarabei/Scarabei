
package com.jfixby.red.android.sys;

import com.jfixby.red.sys.RedSystem;

public class AndroidSystem extends RedSystem {

	@Override
	public void exit () {
		System.out.println("EXIT");
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

}
