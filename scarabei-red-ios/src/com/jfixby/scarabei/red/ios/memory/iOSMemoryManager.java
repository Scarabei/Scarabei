
package com.jfixby.scarabei.red.ios.memory;

import com.jfixby.scarabei.api.memory.MemoryManagerComponent;
import com.jfixby.scarabei.ios.api.iOS;

public class iOSMemoryManager implements MemoryManagerComponent {
	@Override
	public long getMaxHeapSize () {
		return iOS.getMaxHeapSize();
	}

	@Override
	public long getRecommendedHeapSize () {
		return iOS.getRecommendedHeapSize();
	}
}
