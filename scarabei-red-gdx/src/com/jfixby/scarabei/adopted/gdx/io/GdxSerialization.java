/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.jfixby.scarabei.adopted.gdx.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.OrderedMap.OrderedMapValues;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.StreamUtils;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.jfixby.scarabei.adopted.gdx.json.red.JsonReader;

/** Reads/writes Java objects to/from JSON, automatically. See the wiki for usage:
 * https://github.com/libgdx/libgdx/wiki/Reading-%26-writing-JSON
 *
 * @author Nathan Sweet */
public class GdxSerialization<DataType> {
	static private final boolean debug = false;

	private DataWriter<DataType> writer;
	private String typeName = "class";
	private boolean usePrototypes = true;
	// private OutputTypeID outputTypeID;
	// private boolean quoteLongValues;
	private boolean ignoreUnknownFields;
	private boolean enumNames = true;
	private JsonSerializer defaultSerializer;
	private final ObjectMap<Class, OrderedMap<String, FieldMetadata>> typeToFields = new ObjectMap();
	private final ObjectMap<String, Class> tagToClass = new ObjectMap();
	private final ObjectMap<Class, String> classToTag = new ObjectMap();
	private final ObjectMap<Class, JsonSerializer> classToSerializer = new ObjectMap();
	private final ObjectMap<Class, Object[]> classToDefaultValues = new ObjectMap();
	private final Object[] equals1 = {null}, equals2 = {null};

	public GdxSerialization () {
		// outputTypeID = OutputTypeID.minimal;
	}

	// public GdxJson(OutputTypeID outputType) {
	// this.outputTypeID = outputType;
	// }

	/** When true, fields in the JSON that are not found on the class will not throw a {@link SerializationException}. Default is
	 * false. */
	public void setIgnoreUnknownFields (final boolean ignoreUnknownFields) {
		this.ignoreUnknownFields = ignoreUnknownFields;
	}

	// /** @see JsonWriter#setOutputType(OutputType) */
	// public void setOutputType(OutputTypeID outputType) {
	// this.outputTypeID = outputType;
	// }
	//
	// /** @see JsonWriter#setQuoteLongValues(boolean) */
	// public void setQuoteLongValues(boolean quoteLongValues) {
	// this.quoteLongValues = quoteLongValues;
	// }

	/** When true, {@link Enum#name()} is used to write enum values. When false, {@link Enum#toString()} is used which may not be
	 * unique. Default is true. */
	public void setEnumNames (final boolean enumNames) {
		this.enumNames = enumNames;
	}

	/** Sets a tag to use instead of the fully qualifier class name. This can make the JSON easier to read. */
	public void addClassTag (final String tag, final Class type) {
		this.tagToClass.put(tag, type);
		this.classToTag.put(type, tag);
	}

	/** Returns the class for the specified tag, or null. */
	public Class getClass (final String tag) {
		return this.tagToClass.get(tag);
	}

	/** Returns the tag for the specified class, or null. */
	public String getTag (final Class type) {
		return this.classToTag.get(type);
	}

	/** Sets the name of the JSON field to store the Java class name or class tag when required to avoid ambiguity during
	 * deserialization. Set to null to never output this information, but be warned that deserialization may fail. Default is
	 * "class". */
	public void setTypeName (final String typeName) {
		this.typeName = typeName;
	}

	/** Sets the serializer to use when the type being deserialized is not known (null).
	 * 
	 * @param defaultSerializer May be null. */
	public void setDefaultSerializer (final JsonSerializer defaultSerializer) {
		this.defaultSerializer = defaultSerializer;
	}

	/** Registers a serializer to use for the specified type instead of the default behavior of serializing all of an objects
	 * fields. */
	public <T> void setSerializer (final Class<T> type, final JsonSerializer<T> serializer) {
		this.classToSerializer.put(type, serializer);
	}

	public <T> JsonSerializer<T> getSerializer (final Class<T> type) {
		return this.classToSerializer.get(type);
	}

	/** When true, field values that are identical to a newly constructed instance are not written. Default is true. */
	public void setUsePrototypes (final boolean usePrototypes) {
		this.usePrototypes = usePrototypes;
	}

	/** Sets the type of elements in a collection. When the element type is known, the class for each element in the collection
	 * does not need to be written unless different from the element type. */
	public void setElementType (final Class type, final String fieldName, final Class elementType) {
		final ObjectMap<String, FieldMetadata> fields = this.getFields(type);
		final FieldMetadata metadata = fields.get(fieldName);
		if (metadata == null) {
			throw new SerializationException("Field not found: " + fieldName + " (" + type.getName() + ")");
		}
		metadata.elementType = elementType;
	}

	private OrderedMap<String, FieldMetadata> getFields (final Class type) {
		final OrderedMap<String, FieldMetadata> fields = this.typeToFields.get(type);
		if (fields != null) {
			return fields;
		}

		final Array<Class> classHierarchy = new Array();
		Class nextClass = type;
		while (nextClass != Object.class) {
			classHierarchy.add(nextClass);
			nextClass = nextClass.getSuperclass();
		}
		final ArrayList<Field> allFields = new ArrayList();
		for (int i = classHierarchy.size - 1; i >= 0; i--) {
			Collections.addAll(allFields, ClassReflection.getDeclaredFields(classHierarchy.get(i)));
		}

		final OrderedMap<String, FieldMetadata> nameToField = new OrderedMap(allFields.size());
		for (int i = 0, n = allFields.size(); i < n; i++) {
			final Field field = allFields.get(i);

			if (field.isTransient()) {
				continue;
			}
			if (field.isStatic()) {
				continue;
			}
			if (field.isSynthetic()) {
				continue;
			}

			if (!field.isAccessible()) {
				try {
					field.setAccessible(true);
				} catch (final AccessControlException ex) {
					continue;
				}
			}

			nameToField.put(field.getName(), new FieldMetadata(field));
		}
		this.typeToFields.put(type, nameToField);
		return nameToField;
	}

	public DataType serialize (final Object object) {
		return this.toJsonRed(object, object == null ? null : object.getClass(), (Class)null);
	}

	public DataType toJsonRed (final Object object, final Class knownType) {
		return this.toJsonRed(object, knownType, (Class)null);
	}

	/** @param knownType May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown. */
	public DataType toJsonRed (final Object object, final Class knownType, final Class elementType) {

		this.toJsonBuff(object, knownType, elementType);
		return this.writer.toOutputData();
	}

	public void toJsonBuff (final Object object) {
		this.toJsonBuff(object, object == null ? null : object.getClass(), null);
	}

	/** @param knownType May be null if the type is unknown. */
	public void toJsonBuff (final Object object, final Class knownType) {
		this.toJsonBuff(object, knownType, null);
	}

	/** @param knownType May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown. */
	public void toJsonBuff (final Object object, final Class knownType, final Class elementType) {

		this.writer = this.writer;

		try {
			this.writeValue(object, knownType, elementType);
		} finally {
			StreamUtils.closeQuietly(this.writer);
			// this.writer = null;
		}
	}

	protected void setWriter (final DataWriter<DataType> writer) {
		this.writer = writer;
	}

	/** Writes all fields of the specified object to the current JSON object. */
	public void writeFields (final Object object) {
		final Class type = object.getClass();

		final Object[] defaultValues = this.getDefaultValues(type);

		final OrderedMap<String, FieldMetadata> fields = this.getFields(type);
		int i = 0;
		for (final FieldMetadata metadata : new OrderedMapValues<FieldMetadata>(fields)) {
			final Field field = metadata.field;
			try {
				final Object value = field.get(object);
				if (defaultValues != null) {
					final Object defaultValue = defaultValues[i++];
					if (value == null && defaultValue == null) {
						continue;
					}
					if (value != null && defaultValue != null) {
						if (value.equals(defaultValue)) {
							continue;
						}
						if (value.getClass().isArray() && defaultValue.getClass().isArray()) {
							this.equals1[0] = value;
							this.equals2[0] = defaultValue;
							if (Arrays.deepEquals(this.equals1, this.equals2)) {
								continue;
							}
						}
					}
				}

				if (debug) {
					System.out.println("Writing field: " + field.getName() + " (" + type.getName() + ")");
				}
				this.writer.writeName(field.getName());
				this.writeValue(value, field.getType(), metadata.elementType);
			} catch (final ReflectionException ex) {
				throw new SerializationException("Error accessing field: " + field.getName() + " (" + type.getName() + ")", ex);
			} catch (final SerializationException ex) {
				ex.addTrace(field + " (" + type.getName() + ")");
				throw ex;
			} catch (final Exception runtimeEx) {
				final SerializationException ex = new SerializationException(runtimeEx);
				ex.addTrace(field + " (" + type.getName() + ")");
				throw ex;
			}
		}
	}

	private Object[] getDefaultValues (final Class type) {
		if (!this.usePrototypes) {
			return null;
		}
		if (this.classToDefaultValues.containsKey(type)) {
			return this.classToDefaultValues.get(type);
		}
		Object object;
		try {
			object = this.newInstance(type);
		} catch (final Exception ex) {
			this.classToDefaultValues.put(type, null);
			return null;
		}

		final ObjectMap<String, FieldMetadata> fields = this.getFields(type);
		final Object[] values = new Object[fields.size];
		this.classToDefaultValues.put(type, values);

		int i = 0;
		for (final FieldMetadata metadata : fields.values()) {
			final Field field = metadata.field;
			try {
				values[i++] = field.get(object);
			} catch (final ReflectionException ex) {
				throw new SerializationException("Error accessing field: " + field.getName() + " (" + type.getName() + ")", ex);
			} catch (final SerializationException ex) {
				ex.addTrace(field + " (" + type.getName() + ")");
				throw ex;
			} catch (final RuntimeException runtimeEx) {
				final SerializationException ex = new SerializationException(runtimeEx);
				ex.addTrace(field + " (" + type.getName() + ")");
				throw ex;
			}
		}
		return values;
	}

	/** @see #writeField(Object, String, String, Class) */
	public void writeField (final Object object, final String name) {
		this.writeField(object, name, name, null);
	}

	/** @param elementType May be null if the type is unknown.
	 * @see #writeField(Object, String, String, Class) */
	public void writeField (final Object object, final String name, final Class elementType) {
		this.writeField(object, name, name, elementType);
	}

	/** @see #writeField(Object, String, String, Class) */
	public void writeField (final Object object, final String fieldName, final String jsonName) {
		this.writeField(object, fieldName, jsonName, null);
	}

	/** Writes the specified field to the current JSON object.
	 * 
	 * @param elementType May be null if the type is unknown. */
	public void writeField (final Object object, final String fieldName, final String jsonName, Class elementType) {
		final Class type = object.getClass();
		final ObjectMap<String, FieldMetadata> fields = this.getFields(type);
		final FieldMetadata metadata = fields.get(fieldName);
		if (metadata == null) {
			throw new SerializationException("Field not found: " + fieldName + " (" + type.getName() + ")");
		}
		final Field field = metadata.field;
		if (elementType == null) {
			elementType = metadata.elementType;
		}
		try {
			if (debug) {
				System.out.println("Writing field: " + field.getName() + " (" + type.getName() + ")");
			}
			this.writer.writeName(jsonName);
			this.writeValue(field.get(object), field.getType(), elementType);
		} catch (final ReflectionException ex) {
			throw new SerializationException("Error accessing field: " + field.getName() + " (" + type.getName() + ")", ex);
		} catch (final SerializationException ex) {
			ex.addTrace(field + " (" + type.getName() + ")");
			throw ex;
		} catch (final Exception runtimeEx) {
			final SerializationException ex = new SerializationException(runtimeEx);
			ex.addTrace(field + " (" + type.getName() + ")");
			throw ex;
		}
	}

	/** Writes the value as a field on the current JSON object, without writing the actual class.
	 * 
	 * @param value May be null.
	 * @see #writeValue(String, Object, Class, Class) */
	public void writeValue (final String name, final Object value) {
		try {
			this.writer.writeName(name);
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
		if (value == null) {
			this.writeValue(value, null, null);
		} else {
			this.writeValue(value, value.getClass(), null);
		}
	}

	/** Writes the value as a field on the current JSON object, writing the class of the object if it differs from the specified
	 * known type.
	 * 
	 * @param value May be null.
	 * @param knownType May be null if the type is unknown.
	 * @see #writeValue(String, Object, Class, Class) */
	public void writeValue (final String name, final Object value, final Class knownType) {
		try {
			this.writer.writeName(name);
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
		this.writeValue(value, knownType, null);
	}

	/** Writes the value as a field on the current JSON object, writing the class of the object if it differs from the specified
	 * known type. The specified element type is used as the default type for collections.
	 * 
	 * @param value May be null.
	 * @param knownType May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown. */
	public void writeValue (final String name, final Object value, final Class knownType, final Class elementType) {
		try {
			this.writer.writeName(name);
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
		this.writeValue(value, knownType, elementType);
	}

	/** Writes the value, without writing the class of the object.
	 * 
	 * @param value May be null. */
	public void writeValue (final Object value) {
		if (value == null) {
			this.writeValue(value, null, null);
		} else {
			this.writeValue(value, value.getClass(), null);
		}
	}

	/** Writes the value, writing the class of the object if it differs from the specified known type.
	 * 
	 * @param value May be null.
	 * @param knownType May be null if the type is unknown. */
	public void writeValue (final Object value, final Class knownType) {
		this.writeValue(value, knownType, null);
	}

	/** Writes the value, writing the class of the object if it differs from the specified known type. The specified element type
	 * is used as the default type for collections.
	 * 
	 * @param value May be null.
	 * @param knownType May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown. */
	public void writeValue (final Object value, Class knownType, Class elementType) {
		try {
			if (value == null) {
				this.writer.value(null);
				return;
			}

			if ((knownType != null && knownType.isPrimitive()) || knownType == String.class || knownType == Integer.class
				|| knownType == Boolean.class || knownType == Float.class || knownType == Long.class || knownType == Double.class
				|| knownType == Short.class || knownType == Byte.class || knownType == Character.class) {
				this.writer.value(value);
				return;
			}

			Class actualType = value.getClass();

			if (actualType.isPrimitive() || actualType == String.class || actualType == Integer.class || actualType == Boolean.class
				|| actualType == Float.class || actualType == Long.class || actualType == Double.class || actualType == Short.class
				|| actualType == Byte.class || actualType == Character.class) {
				this.writeObjectStart(actualType, null);
				this.writeValue("value", value);
				this.writeObjectEnd();
				return;
			}

			if (value instanceof JsonSerializable) {
				this.writeObjectStart(actualType, knownType);
				((JsonSerializable)value).write(this);
				this.writeObjectEnd();
				return;
			}

			final JsonSerializer serializer = this.classToSerializer.get(actualType);
			if (serializer != null) {
				serializer.write(this, value, knownType);
				return;
			}

			// JSON array special cases.
			if (value instanceof Array) {
				if (knownType != null && actualType != knownType && actualType != Array.class) {
					throw new SerializationException("Serialization of an Array other than the known type is not supported.\n"
						+ "Known type: " + knownType + "\nActual type: " + actualType);
				}
				this.writeArrayStart();
				final Array array = (Array)value;
				for (int i = 0, n = array.size; i < n; i++) {
					this.writeValue(array.get(i), elementType, null);
				}
				this.writeArrayEnd();
				return;
			}
			if (value instanceof Queue) {
				if (knownType != null && actualType != knownType && actualType != Queue.class) {
					throw new SerializationException("Serialization of a Queue other than the known type is not supported.\n"
						+ "Known type: " + knownType + "\nActual type: " + actualType);
				}
				this.writeArrayStart();
				final Queue queue = (Queue)value;
				for (int i = 0, n = queue.size; i < n; i++) {
					this.writeValue(queue.get(i), elementType, null);
				}
				this.writeArrayEnd();
				return;
			}
			if (value instanceof Collection) {
				if (this.typeName != null && actualType != ArrayList.class && (knownType == null || knownType != actualType)) {
					this.writeObjectStart(actualType, knownType);
					this.writeArrayStart("items");
					for (final Object item : (Collection)value) {
						this.writeValue(item, elementType, null);
					}
					this.writeArrayEnd();
					this.writeObjectEnd();
				} else {
					this.writeArrayStart();
					for (final Object item : (Collection)value) {
						this.writeValue(item, elementType, null);
					}
					this.writeArrayEnd();
				}
				return;
			}
			if (actualType.isArray()) {
				if (elementType == null) {
					elementType = actualType.getComponentType();
				}
				final int length = ArrayReflection.getLength(value);
				this.writeArrayStart();
				for (int i = 0; i < length; i++) {
					this.writeValue(ArrayReflection.get(value, i), elementType, null);
				}
				this.writeArrayEnd();
				return;
			}

			// JSON object special cases.
			if (value instanceof ObjectMap) {
				if (knownType == null) {
					knownType = ObjectMap.class;
				}
				this.writeObjectStart(actualType, knownType);
				for (final Entry entry : ((ObjectMap<?, ?>)value).entries()) {
					this.writer.writeName(this.convertToString(entry.key));
					this.writeValue(entry.value, elementType, null);
				}
				this.writeObjectEnd();
				return;
			}
			if (value instanceof ArrayMap) {
				if (knownType == null) {
					knownType = ArrayMap.class;
				}
				this.writeObjectStart(actualType, knownType);
				final ArrayMap map = (ArrayMap)value;
				for (int i = 0, n = map.size; i < n; i++) {
					this.writer.writeName(this.convertToString(map.keys[i]));
					this.writeValue(map.values[i], elementType, null);
				}
				this.writeObjectEnd();
				return;
			}
			if (value instanceof Map) {
				if (knownType == null) {
					knownType = HashMap.class;
				}
				this.writeObjectStart(actualType, knownType);
				for (final Map.Entry entry : ((Map<?, ?>)value).entrySet()) {
					this.writer.writeName(this.convertToString(entry.getKey()));
					this.writeValue(entry.getValue(), elementType, null);
				}
				this.writeObjectEnd();
				return;
			}

			// Enum special case.
			if (ClassReflection.isAssignableFrom(Enum.class, actualType)) {
				if (this.typeName != null && (knownType == null || knownType != actualType)) {
					// Ensures that enums with specific implementations
					// (abstract logic) serialize correctly.
					if (actualType.getEnumConstants() == null) {
						actualType = actualType.getSuperclass();
					}

					this.writeObjectStart(actualType, null);
					this.writer.writeName("value");
					this.writer.value(this.convertToString((Enum)value));
					this.writeObjectEnd();
				} else {
					this.writer.value(this.convertToString((Enum)value));
				}
				return;
			}

			this.writeObjectStart(actualType, knownType);
			this.writeFields(value);
			this.writeObjectEnd();
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
	}

	public void writeObjectStart (final String name) {
		try {
			this.writer.writeName(name);
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
		this.writeObjectStart();
	}

	/** @param knownType May be null if the type is unknown. */
	public void writeObjectStart (final String name, final Class actualType, final Class knownType) {
		try {
			this.writer.writeName(name);
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
		this.writeObjectStart(actualType, knownType);
	}

	public void writeObjectStart () {
		try {
			this.writer.object();
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
	}

	/** Starts writing an object, writing the actualType to a field if needed.
	 * 
	 * @param knownType May be null if the type is unknown. */
	public void writeObjectStart (final Class actualType, final Class knownType) {
		try {
			this.writer.object();
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
		if (knownType == null || knownType != actualType) {
			this.writeType(actualType);
		}
	}

	public void writeObjectEnd () {
		try {
			this.writer.pop();
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
	}

	public void writeArrayStart (final String name) {
		try {
			this.writer.writeName(name);
			this.writer.array();
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
	}

	public void writeArrayStart () {
		try {
			this.writer.array();
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
	}

	public void writeArrayEnd () {
		try {
			this.writer.pop();
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
	}

	public void writeType (final Class type) {
		if (this.typeName == null) {
			return;
		}
		String className = this.getTag(type);
		if (className == null) {
			className = type.getName();
		}
		try {
			this.writer.set(this.typeName, className);
		} catch (final IOException ex) {
			throw new SerializationException(ex);
		}
		if (debug) {
			System.out.println("Writing type: " + type.getName());
		}
	}

	/** @param type May be null if the type is unknown.
	 * @return May be null. */
	public <T> T fromJson (final Class<T> type, final Reader reader) {
		return this.readValue(type, null, new JsonReader().parse(reader));
	}

	/** @param type May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown.
	 * @return May be null. */
	public <T> T fromJson (final Class<T> type, final Class elementType, final Reader reader) {
		return this.readValue(type, elementType, new JsonReader().parse(reader));
	}

	/** @param type May be null if the type is unknown.
	 * @return May be null. */
	public <T> T fromJson (final Class<T> type, final InputStream input) {
		return this.readValue(type, null, new JsonReader().parse(input));
	}

	/** @param type May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown.
	 * @return May be null. */
	public <T> T fromJson (final Class<T> type, final Class elementType, final InputStream input) {
		return this.readValue(type, elementType, new JsonReader().parse(input));
	}

	/** @param type May be null if the type is unknown.
	 * @return May be null. */
	public <T> T fromJson (final Class<T> type, final char[] data, final int offset, final int length) {
		return this.readValue(type, null, new JsonReader().parse(data, offset, length));
	}

	/** @param type May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown.
	 * @return May be null. */
	public <T> T fromJson (final Class<T> type, final Class elementType, final char[] data, final int offset, final int length) {
		return this.readValue(type, elementType, new JsonReader().parse(data, offset, length));
	}

	/** @param type May be null if the type is unknown.
	 * @return May be null. */
	public <T> T deSerialize (final Class<T> type, final DataType json, final DataReader<DataType> reader) {
		return this.readValue(type, null, reader.parse(json));
	}

	public void readField (final Object object, final String name, final JsonValue jsonData) {
		this.readField(object, name, name, null, jsonData);
	}

	public void readField (final Object object, final String name, final Class elementType, final JsonValue jsonData) {
		this.readField(object, name, name, elementType, jsonData);
	}

	public void readField (final Object object, final String fieldName, final String jsonName, final JsonValue jsonData) {
		this.readField(object, fieldName, jsonName, null, jsonData);
	}

	/** @param elementType May be null if the type is unknown. */
	public void readField (final Object object, final String fieldName, final String jsonName, Class elementType,
		final JsonValue jsonMap) {
		final Class type = object.getClass();
		final ObjectMap<String, FieldMetadata> fields = this.getFields(type);
		final FieldMetadata metadata = fields.get(fieldName);
		if (metadata == null) {
			throw new SerializationException("Field not found: " + fieldName + " (" + type.getName() + ")");
		}
		final Field field = metadata.field;
		if (elementType == null) {
			elementType = metadata.elementType;
		}
		this.readField(object, field, jsonName, elementType, jsonMap);
	}

	/** @param object May be null if the field is static.
	 * @param elementType May be null if the type is unknown. */
	public void readField (final Object object, final Field field, final String jsonName, final Class elementType,
		final JsonValue jsonMap) {
		final JsonValue jsonValue = jsonMap.get(jsonName);
		if (jsonValue == null) {
			return;
		}
		try {
			field.set(object, this.readValue(field.getType(), elementType, jsonValue));
		} catch (final ReflectionException ex) {
			throw new SerializationException(
				"Error accessing field: " + field.getName() + " (" + field.getDeclaringClass().getName() + ")", ex);
		} catch (final SerializationException ex) {
			ex.addTrace(field.getName() + " (" + field.getDeclaringClass().getName() + ")");
			throw ex;
		} catch (final RuntimeException runtimeEx) {
			final SerializationException ex = new SerializationException(runtimeEx);
			ex.addTrace(jsonValue.trace());
			ex.addTrace(field.getName() + " (" + field.getDeclaringClass().getName() + ")");
			throw ex;
		}
	}

	public void readFields (final Object object, final JsonValue jsonMap) {
		final Class type = object.getClass();
		final ObjectMap<String, FieldMetadata> fields = this.getFields(type);
		for (JsonValue child = jsonMap.child; child != null; child = child.next) {
			final FieldMetadata metadata = fields.get(child.name);
			if (metadata == null) {
				if (child.name.equals(this.typeName)) {
					continue;
				}
				if (this.ignoreUnknownFields) {
					if (debug) {
						System.out.println("Ignoring unknown field: " + child.name + " (" + type.getName() + ")");
					}
					continue;
				} else {
					final SerializationException ex = new SerializationException(
						"Field not found: " + child.name + " (" + type.getName() + ")");
					ex.addTrace(child.trace());
					throw ex;
				}
			}
			final Field field = metadata.field;
			try {
				field.set(object, this.readValue(field.getType(), metadata.elementType, child));
			} catch (final ReflectionException ex) {
				throw new SerializationException("Error accessing field: " + field.getName() + " (" + type.getName() + ")", ex);
			} catch (final SerializationException ex) {
				ex.addTrace(field.getName() + " (" + type.getName() + ")");
				throw ex;
			} catch (final RuntimeException runtimeEx) {
				final SerializationException ex = new SerializationException(runtimeEx);
				ex.addTrace(child.trace());
				ex.addTrace(field.getName() + " (" + type.getName() + ")");
				throw ex;
			}
		}
	}

	/** @param type May be null if the type is unknown.
	 * @return May be null. */
	public <T> T readValue (final String name, final Class<T> type, final JsonValue jsonMap) {
		return this.readValue(type, null, jsonMap.get(name));
	}

	/** @param type May be null if the type is unknown.
	 * @return May be null. */
	public <T> T readValue (final String name, final Class<T> type, final T defaultValue, final JsonValue jsonMap) {
		final JsonValue jsonValue = jsonMap.get(name);
		if (jsonValue == null) {
			return defaultValue;
		}
		return this.readValue(type, null, jsonValue);
	}

	/** @param type May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown.
	 * @return May be null. */
	public <T> T readValue (final String name, final Class<T> type, final Class elementType, final JsonValue jsonMap) {
		return this.readValue(type, elementType, jsonMap.get(name));
	}

	/** @param type May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown.
	 * @return May be null. */
	public <T> T readValue (final String name, final Class<T> type, final Class elementType, final T defaultValue,
		final JsonValue jsonMap) {
		final JsonValue jsonValue = jsonMap.get(name);
		return this.readValue(type, elementType, defaultValue, jsonValue);
	}

	/** @param type May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown.
	 * @return May be null. */
	public <T> T readValue (final Class<T> type, final Class elementType, final T defaultValue, final JsonValue jsonData) {
		if (jsonData == null) {
			return defaultValue;
		}
		return this.readValue(type, elementType, jsonData);
	}

	/** @param type May be null if the type is unknown.
	 * @return May be null. */
	public <T> T readValue (final Class<T> type, final JsonValue jsonData) {
		return this.readValue(type, null, jsonData);
	}

	/** @param type May be null if the type is unknown.
	 * @param elementType May be null if the type is unknown.
	 * @return May be null. */
	public <T> T readValue (Class<T> type, Class elementType, JsonValue jsonData) {
		if (jsonData == null) {
			return null;
		}

		if (jsonData.isObject()) {
			final String className = this.typeName == null ? null : jsonData.getString(this.typeName, null);
			if (className != null) {
				type = this.getClass(className);
				if (type == null) {
					try {
						type = ClassReflection.forName(className);
					} catch (final ReflectionException ex) {
						throw new SerializationException(ex);
					}
				}
			}

			if (type == null) {
				if (this.defaultSerializer != null) {
					return (T)this.defaultSerializer.read(this, jsonData, type);
				}
				return (T)jsonData;
			}

			if (this.typeName != null && ClassReflection.isAssignableFrom(Collection.class, type)) {
				// JSON object wrapper to specify type.
				jsonData = jsonData.get("items");
			} else {
				final JsonSerializer serializer = this.classToSerializer.get(type);
				if (serializer != null) {
					return (T)serializer.read(this, jsonData, type);
				}

				if (type == String.class || type == Integer.class || type == Boolean.class || type == Float.class
					|| type == Long.class || type == Double.class || type == Short.class || type == Byte.class
					|| type == Character.class || ClassReflection.isAssignableFrom(Enum.class, type)) {
					return this.readValue("value", type, jsonData);
				}

				final Object object = this.newInstance(type);

				if (object instanceof JsonSerializable) {
					((JsonSerializable)object).read(this, jsonData);
					return (T)object;
				}

				// JSON object special cases.
				if (object instanceof ObjectMap) {
					final ObjectMap result = (ObjectMap)object;
					for (JsonValue child = jsonData.child; child != null; child = child.next) {
						result.put(child.name, this.readValue(elementType, null, child));
					}
					return (T)result;
				}
				if (object instanceof ArrayMap) {
					final ArrayMap result = (ArrayMap)object;
					for (JsonValue child = jsonData.child; child != null; child = child.next) {
						result.put(child.name, this.readValue(elementType, null, child));
					}
					return (T)result;
				}
				if (object instanceof Map) {
					final Map result = (Map)object;
					for (JsonValue child = jsonData.child; child != null; child = child.next) {
						result.put(child.name, this.readValue(elementType, null, child));
					}
					return (T)result;
				}

				this.readFields(object, jsonData);
				return (T)object;
			}
		}

		if (type != null) {
			final JsonSerializer serializer = this.classToSerializer.get(type);
			if (serializer != null) {
				return (T)serializer.read(this, jsonData, type);
			}
		}

		if (jsonData.isArray()) {
			// JSON array special cases.
			if (type == null || type == Object.class) {
				type = (Class<T>)Array.class;
			}
			if (ClassReflection.isAssignableFrom(Array.class, type)) {
				final Array result = type == Array.class ? new Array() : (Array)this.newInstance(type);
				for (JsonValue child = jsonData.child; child != null; child = child.next) {
					result.add(this.readValue(elementType, null, child));
				}
				return (T)result;
			}
			if (ClassReflection.isAssignableFrom(Queue.class, type)) {
				final Queue result = type == Queue.class ? new Queue() : (Queue)this.newInstance(type);
				for (JsonValue child = jsonData.child; child != null; child = child.next) {
					result.addLast(this.readValue(elementType, null, child));
				}
				return (T)result;
			}
			if (ClassReflection.isAssignableFrom(Collection.class, type)) {
				final Collection result = type.isInterface() ? new ArrayList() : (Collection)this.newInstance(type);
				for (JsonValue child = jsonData.child; child != null; child = child.next) {
					result.add(this.readValue(elementType, null, child));
				}
				return (T)result;
			}
			if (type.isArray()) {
				final Class componentType = type.getComponentType();
				if (elementType == null) {
					elementType = componentType;
				}
				final Object result = ArrayReflection.newInstance(componentType, jsonData.size);
				int i = 0;
				for (JsonValue child = jsonData.child; child != null; child = child.next) {
					ArrayReflection.set(result, i++, this.readValue(elementType, null, child));
				}
				return (T)result;
			}
			throw new SerializationException("Unable to convert value to required type: " + jsonData + " (" + type.getName() + ")");
		}

		if (jsonData.isNumber()) {
			try {
				if (type == null || type == float.class || type == Float.class) {
					return (T)(Float)jsonData.asFloat();
				}
				if (type == int.class || type == Integer.class) {
					return (T)(Integer)jsonData.asInt();
				}
				if (type == long.class || type == Long.class) {
					return (T)(Long)jsonData.asLong();
				}
				if (type == double.class || type == Double.class) {
					return (T)(Double)jsonData.asDouble();
				}
				if (type == String.class) {
					return (T)jsonData.asString();
				}
				if (type == short.class || type == Short.class) {
					return (T)(Short)jsonData.asShort();
				}
				if (type == byte.class || type == Byte.class) {
					return (T)(Byte)jsonData.asByte();
				}
			} catch (final NumberFormatException ignored) {
			}
			jsonData = new JsonValue(jsonData.asString());
		}

		if (jsonData.isBoolean()) {
			try {
				if (type == null || type == boolean.class || type == Boolean.class) {
					return (T)(Boolean)jsonData.asBoolean();
				}
			} catch (final NumberFormatException ignored) {
			}
			jsonData = new JsonValue(jsonData.asString());
		}

		if (jsonData.isString()) {
			final String string = jsonData.asString();
			if (type == null || type == String.class) {
				return (T)string;
			}
			try {
				if (type == int.class || type == Integer.class) {
					return (T)Integer.valueOf(string);
				}
				if (type == float.class || type == Float.class) {
					return (T)Float.valueOf(string);
				}
				if (type == long.class || type == Long.class) {
					return (T)Long.valueOf(string);
				}
				if (type == double.class || type == Double.class) {
					return (T)Double.valueOf(string);
				}
				if (type == short.class || type == Short.class) {
					return (T)Short.valueOf(string);
				}
				if (type == byte.class || type == Byte.class) {
					return (T)Byte.valueOf(string);
				}
			} catch (final NumberFormatException ignored) {
			}
			if (type == boolean.class || type == Boolean.class) {
				return (T)Boolean.valueOf(string);
			}
			if (type == char.class || type == Character.class) {
				return (T)(Character)string.charAt(0);
			}
			if (ClassReflection.isAssignableFrom(Enum.class, type)) {
				final Enum[] constants = (Enum[])type.getEnumConstants();
				for (int i = 0, n = constants.length; i < n; i++) {
					final Enum e = constants[i];
					if (string.equals(this.convertToString(e))) {
						return (T)e;
					}
				}
			}
			if (type == CharSequence.class) {
				return (T)string;
			}
			throw new SerializationException("Unable to convert value to required type: " + jsonData + " (" + type.getName() + ")");
		}

		return null;
	}

	private String convertToString (final Enum e) {
		return this.enumNames ? e.name() : e.toString();
	}

	private String convertToString (final Object object) {
		if (object instanceof Enum) {
			return this.convertToString((Enum)object);
		}
		if (object instanceof Class) {
			return ((Class)object).getName();
		}
		return String.valueOf(object);
	}

	protected Object newInstance (Class type) {
		try {
			return ClassReflection.newInstance(type);
		} catch (Exception ex) {
			try {
				// Try a private constructor.
				final Constructor constructor = ClassReflection.getDeclaredConstructor(type);
				constructor.setAccessible(true);
				return constructor.newInstance();
			} catch (final SecurityException ignored) {
			} catch (final ReflectionException ignored) {
				if (ClassReflection.isAssignableFrom(Enum.class, type)) {
					if (type.getEnumConstants() == null) {
						type = type.getSuperclass();
					}
					return type.getEnumConstants()[0];
				}
				if (type.isArray()) {
					throw new SerializationException("Encountered JSON object when expected array of type: " + type.getName(), ex);
				} else if (ClassReflection.isMemberClass(type) && !ClassReflection.isStaticClass(type)) {
					throw new SerializationException("Class cannot be created (non-static member class): " + type.getName(), ex);
				} else {
					throw new SerializationException("Class cannot be created (missing no-arg constructor): " + type.getName(), ex);
				}
			} catch (final Exception privateConstructorException) {
				ex = privateConstructorException;
			}
			throw new SerializationException("Error constructing instance of class: " + type.getName(), ex);
		}
	}

	// public OutputType prettyPrint(Object object) {
	// return prettyPrint(object, 0);
	// }
	//
	// public OutputType prettyPrint(OutputType json) {
	// return prettyPrint(json, 0);
	// }
	//
	// public OutputType prettyPrint(Object object, int singleLineColumns) {
	// return prettyPrint(toJson(object), singleLineColumns);
	// }
	//
	// public OutputType prettyPrint(OutputType json, int singleLineColumns) {
	// return new JsonReader().parse(json).prettyPrint(outputTypeID,
	// singleLineColumns);
	// }
	//
	// public OutputType prettyPrint(Object object, PrettyPrintSettings
	// settings) {
	// return prettyPrint(toJson(object), settings);
	// }
	//
	// public OutputType prettyPrint(OutputType json, PrettyPrintSettings
	// settings) {
	// return new JsonReader().parse(json).prettyPrint(settings);
	// }

}
