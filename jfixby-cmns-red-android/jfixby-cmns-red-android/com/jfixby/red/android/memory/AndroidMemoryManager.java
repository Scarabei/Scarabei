
package com.jfixby.red.android.memory;

import com.jfixby.android.api.Android;
import com.jfixby.cmns.api.memory.MemoryManagerComponent;

public class AndroidMemoryManager implements MemoryManagerComponent {

	@Override
	public long getMaxHeapSize () {
		return Android.getMaxHeapSize();
	}

	@Override
	public long getRecommendedHeapSize () {
		return Android.getRecommendedHeapSize();
	}

}
