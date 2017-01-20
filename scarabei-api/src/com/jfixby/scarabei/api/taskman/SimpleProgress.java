
package com.jfixby.scarabei.api.taskman;

public interface SimpleProgress extends TaskProgress {

	void updateProcessed (float processed);

	void setTotal (int total);

	void setIsDone ();

}
