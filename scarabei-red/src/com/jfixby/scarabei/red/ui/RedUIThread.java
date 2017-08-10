
package com.jfixby.scarabei.red.ui;

import com.jfixby.scarabei.api.ui.UIThreadComponent;

public class RedUIThread implements UIThreadComponent {

	private Thread thread;
	private Thread current;

	@Override
	public void registerUIThread () {
		this.thread = Thread.currentThread();
	}

	@Override
	public boolean isUIThread () {
		this.current = Thread.currentThread();
		return this.thread == this.current;
	}

}
