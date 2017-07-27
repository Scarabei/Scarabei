
package com.jfixby.scarabei.red.color;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.color.CachedColorProjector;
import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.color.ColorProjector;
import com.jfixby.scarabei.api.debug.Debug;

public class RedCachedColorProjector implements CachedColorProjector {

	private ColorProjector base;
	final Map<Color, Color> cache = Collections.newMap();

	public RedCachedColorProjector (ColorProjector input) {
		this.setBase(input);
	}

	@Override
	public Color findClosest (Color other) {
		Color value = cache.get(other);
		if (value == null) {
			value = this.base.findClosest(other);
			this.cache.put(other, value);
		}
		return value;
	}

	@Override
	public void clear () {
		cache.clear();
	}

	@Override
	public void setBase (ColorProjector input) {
		this.base = Debug.checkNull(input);
	}

}
