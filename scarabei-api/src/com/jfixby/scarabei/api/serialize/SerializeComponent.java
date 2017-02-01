
package com.jfixby.scarabei.api.serialize;

import java.util.HashMap;

import com.jfixby.scarabei.api.collections.Map;

public interface SerializeComponent {

	<P, Q> HashMap<P, Q> serialize (Map<P, Q> input);

}
