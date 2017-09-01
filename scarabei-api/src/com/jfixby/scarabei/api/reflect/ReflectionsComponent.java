
package com.jfixby.scarabei.api.reflect;

import com.jfixby.scarabei.api.names.ID;

public interface ReflectionsComponent {

	Class<?> resolveClass (ID className) throws ClassNotFoundException;

	ID nameOf (Class<?> klass);

}
