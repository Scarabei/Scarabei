
package com.jfixby.scarabei.api.taskman;

public interface SimpleProgress extends TaskProgress {

	void updateProcessed (long processed);

	void setTotal (long total);

	void setIsDone ();

}
