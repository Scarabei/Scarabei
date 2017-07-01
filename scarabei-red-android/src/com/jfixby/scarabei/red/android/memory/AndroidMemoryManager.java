
package com.jfixby.scarabei.red.android.memory;

import com.jfixby.scarabei.android.api.Android;
import com.jfixby.scarabei.api.memory.MemoryManagerComponent;

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
