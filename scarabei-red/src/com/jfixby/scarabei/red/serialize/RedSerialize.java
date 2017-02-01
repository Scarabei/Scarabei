
package com.jfixby.scarabei.red.serialize;

import java.util.HashMap;

import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.serialize.SerializeComponent;

public class RedSerialize implements SerializeComponent {

	@Override
	public <P, Q> HashMap<P, Q> serialize (final Map<P, Q> input) {
		final HashMap<P, Q> result = new HashMap<P, Q>();
		result.putAll(input.toJavaMap());
		return result;
	}

}
