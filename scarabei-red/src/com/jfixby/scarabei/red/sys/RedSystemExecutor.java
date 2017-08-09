
package com.jfixby.scarabei.red.sys;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.taskman.ExecutorComponent;
import com.jfixby.scarabei.api.taskman.SYSTEM_STATE;

public class RedSystemExecutor implements ExecutorComponent {

	private final RedTaskManager taskMan;
	private Thread mainTread;

	public RedSystemExecutor (final RedTaskManager redSystem) {
		this.taskMan = redSystem;
	}

	@Override
	public void onSystemStart () {
		this.expectState(SYSTEM_STATE.NEW);
		this.switchState(SYSTEM_STATE.RUNNING);
		this.switchMainThread();
	}

	@Override
	public void switchMainThread () {
		L.d("Switching main Thread");
		L.d("   from", this.mainTread);
		this.mainTread = Thread.currentThread();
		L.d("     to", this.mainTread);
// Sys.exit();
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

	@Override
	public boolean isMainThread () {
		if (this.mainTread == null) {
			Err.reportError("Main thread is not set");
		}
		return this.mainTread == Thread.currentThread();
	}

}
