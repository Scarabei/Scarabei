package com.jfixby.red.sys;

import com.jfixby.cmns.api.taskman.ExecutorComponent;
import com.jfixby.cmns.api.taskman.SYSTEM_STATE;

public class RedSystemExecutor implements ExecutorComponent {

    private RedTaskManager taskMan;

    public RedSystemExecutor(RedTaskManager redSystem) {
	this.taskMan = redSystem;
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
	taskMan.push();
    }

}
