package com.jfixby.scarabei.adopted.gdx.io;

import java.util.Map;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;

class FieldMetadata {
	Field field;
	Class elementType;

	public FieldMetadata (Field field) {
		this.field = field;
		int index = (ClassReflection.isAssignableFrom(ObjectMap.class, field.getType())
			|| ClassReflection.isAssignableFrom(Map.class, field.getType())) ? 1 : 0;
		this.elementType = field.getElementType(index);
	}
}