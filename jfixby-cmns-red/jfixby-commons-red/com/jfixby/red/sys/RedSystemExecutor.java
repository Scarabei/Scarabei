package com.jfixby.red.sys;

import com.jfixby.cmns.api.sys.ExecutorComponent;
import com.jfixby.cmns.api.sys.SYSTEM_STATE;

public class RedSystemExecutor implements ExecutorComponent {

	private RedSystem redSystem;

	public RedSystemExecutor(RedSystem redSystem) {
		this.redSystem = redSystem;
	}

	@Override
	public void onSystemStart() {
		expectState(SYSTEM_STATE.NEW);
		switchState(SYSTEM_STATE.RUNNING);
	}

	private void switchState(SYSTEM_STATE next_state) {
		this.state = next_state;
	}

	private void expectState(SYSTEM_STATE expected) {
		if (expected != this.state) {
			throw new Error("Wrong state: " + state + ", espected: " + expected);
		}
	}

	private SYSTEM_STATE state = SYSTEM_STATE.NEW;

	@Override
	public void pushTasks() {
		redSystem.push();
	}

}
