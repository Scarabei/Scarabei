
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.taskman.ExecutorComponent;
import com.jfixby.scarabei.api.taskman.SYSTEM_STATE;

public class RedSystemExecutor implements ExecutorComponent {

	private final RedTaskManager taskMan;

	public RedSystemExecutor (final RedTaskManager redSystem) {
		this.taskMan = redSystem;
	}

	@Override
	public void onSystemStart () {
		this.expectState(SYSTEM_STATE.NEW);
		this.switchState(SYSTEM_STATE.RUNNING);
	}

	private void switchState (final SYSTEM_STATE next_state) {
		this.state = next_state;
	}

	private void expectState (final SYSTEM_STATE expected) {
		if (expected != this.state) {
			Err.reportError("Wrong state: " + this.state + ", espected: " + expected);
		}
	}

	private SYSTEM_STATE state = SYSTEM_STATE.NEW;

	@Override
	public void pushTasks () {
		this.taskMan.push();
	}

}
