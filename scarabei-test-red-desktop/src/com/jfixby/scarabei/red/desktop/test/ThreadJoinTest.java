
package com.jfixby.scarabei.red.desktop.test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.sys.Sys;

public class ThreadJoinTest {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();

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
