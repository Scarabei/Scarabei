
package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.desktop.DesktopSetup;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.sys.Sys;

public class ThreadWaitTest {

	public static void main (final String[] args) {
		DesktopSetup.deploy();

		final ThreadWaitTest test = new ThreadWaitTest();
		test.test();

	}

	private void test () {

		for (int i = 0; i < 5; i++) {
			final long k = i + 1;
			final Thread t = new Thread() {

				@Override
				public void run () {
					ThreadWaitTest.this.counter++;
					Sys.sleep(1 * 1000 * k);
					ThreadWaitTest.this.lock(k);

				}

			};

			t.start();

		}

		this.waitForResult();
		L.d("exit");

	}

	int counter = 0;

	synchronized private void waitForResult () {
		while (this.counter > 0) {
			Sys.wait(this);
			Sys.sleep(999);
			L.d("wait", Sys.SystemTime());
		}
	}

	synchronized private void lock (final long k) {
		L.d("lock.notify", k);
		this.counter--;
		this.notify();
	}

}
