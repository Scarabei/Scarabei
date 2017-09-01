
package com.jfixby.scarabei.red.reflect;

import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;
import com.jfixby.scarabei.api.reflect.ReflectionsComponent;

public class RedReflections implements ReflectionsComponent {

	private ID NULL_NAME;

	@Override
	public ID nameOf (final Class<?> type) {
		if (type == null) {
			if (this.NULL_NAME == null) {
				this.NULL_NAME = Names.newID("null");
			}
			return this.NULL_NAME;
		}
		return Names.newID(type.getCanonicalName());
	}

	@Override
	public Class<?> resolveClass (final ID className) throws ClassNotFoundException {
		final ClassLoader classLoader = this.getClass().getClassLoader();
		final Class<?> klass = Class.forName(className.toString(), true, classLoader);
		return klass;
	}

}
