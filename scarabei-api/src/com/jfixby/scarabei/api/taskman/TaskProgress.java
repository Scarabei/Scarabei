
package com.jfixby.scarabei.api.taskman;

import com.jfixby.scarabei.api.util.Progress;

public interface TaskProgress extends Progress {

	public int getTotalSteps ();

	public float getProcessedSteps ();

}
