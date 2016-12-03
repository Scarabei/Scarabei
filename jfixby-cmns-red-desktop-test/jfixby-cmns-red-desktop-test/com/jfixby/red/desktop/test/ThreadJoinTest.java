
package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.Sys;
import com.jfixby.red.desktop.DesktopSetup;

public class ThreadJoinTest {

	public static void main (final String[] args) {
		DesktopSetup.deploy();

		final Object lock = new Object();

		for (int i = 0; i < 5; i++) {
			final long k = i;
			final Thread t = new Thread() {

				@Override
				public void run () {
					Sys.sleep(1000 * k);
					L.d("lock.done", k);
				}
			};
			t.start();

		}

		{
			Sys.wait(lock);
			L.d("wait", Sys.SystemTime());
		}
	}

}
