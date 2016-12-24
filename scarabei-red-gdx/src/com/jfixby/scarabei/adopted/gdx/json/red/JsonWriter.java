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

package com.jfixby.scarabei.adopted.gdx.json.red;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.badlogic.gdx.utils.Array;
import com.jfixby.scarabei.adopted.gdx.io.CharWriter;
import com.jfixby.scarabei.adopted.gdx.io.DataWriter;
import com.jfixby.scarabei.adopted.gdx.io.StringBuffer;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.json.JsonString;

/** Builder style API for emitting JSON.
 *
 * @author Nathan Sweet */
public class JsonWriter implements Closeable, CharWriter, DataWriter<JsonString> {
	final StringBuffer buffer;
	private final Array<JsonObject> stack = new Array();
	private JsonObject current;
	private boolean named;
	private JsonOutputType outputType = JsonOutputType.json;
	private boolean quoteLongValues = false;

	public JsonWriter () {
		this.buffer = new StringBuffer();
	}

	/** Sets the type of JSON output. Default is {@link JsonOutputType#minimal}. */
	public void setOutputType (final JsonOutputType outputType) {
		this.outputType = outputType;
	}

	/** When true, quotes long, double, BigInteger, BigDecimal types to prevent truncation in languages like JavaScript and PHP.
	 * This is not necessary when using libgdx, which handles these types without truncation. Default is false. */
	public void setQuoteLongValues (final boolean quoteLongValues) {
		this.quoteLongValues = quoteLongValues;
	}

	@Override
	public JsonWriter writeName (final String name) throws IOException {
		if (this.current == null || this.current.array) {
			throw new IllegalStateException("Current item must be an object.");
		}

		if (!this.current.needsComma) {
			this.current.needsComma = true;
		} else {
			this.buffer.write(',');
		}

		this.buffer.write(this.outputType.quoteName(name));
		this.buffer.write(':');
		this.named = true;
		return this;
	}

	@Override
	public JsonWriter object () throws IOException {
		this.requireCommaOrName();
		this.stack.add(this.current = new JsonObject(this, false));
		return this;
	}

	@Override
	public JsonWriter array () throws IOException {
		this.requireCommaOrName();
		this.stack.add(this.current = new JsonObject(this, true));
		return this;
	}

	@Override
	public JsonWriter value (Object value) throws IOException {
		if (this.quoteLongValues
			&& (value instanceof Long || value instanceof Double || value instanceof BigDecimal || value instanceof BigInteger)) {
			value = value.toString();
		} else if (value instanceof Number) {
			final Number number = (Number)value;
			final long longValue = number.longValue();
			if (number.doubleValue() == longValue) {
				value = longValue;
			}
		}
		this.requireCommaOrName();
		this.buffer.write(this.outputType.quoteValue(value));
		return this;
	}

	/** Writes the specified JSON value, without quoting or escaping. */
	public JsonWriter json (final String json) throws IOException {
		this.requireCommaOrName();
		this.buffer.write(json);
		return this;
	}

	private void requireCommaOrName () throws IOException {
		if (this.current == null) {
			return;
		}
		if (this.current.array) {
			if (!this.current.needsComma) {
				this.current.needsComma = true;
			} else {
				this.buffer.write(',');
			}
		} else {
			if (!this.named) {
				throw new IllegalStateException("Name must be set.");
			}
			this.named = false;
		}
	}

	public JsonWriter object (final String name) throws IOException {
		return this.writeName(name).object();
	}

	public JsonWriter array (final String name) throws IOException {
		return this.writeName(name).array();
	}

	@Override
	public JsonWriter set (final String name, final Object value) throws IOException {
		return this.writeName(name).value(value);
	}

	/** Writes the specified JSON value, without quoting or escaping. */
	public JsonWriter json (final String name, final String json) throws IOException {
		return this.writeName(name).json(json);
	}

	@Override
	public JsonWriter pop () throws IOException {
		if (this.named) {
			throw new IllegalStateException("Expected an object, array, or value since a name was set.");
		}
		this.stack.pop().close();
		this.current = this.stack.size == 0 ? null : this.stack.peek();
		return this;
	}

	@Override
	public void write (final char[] cbuf, final int off, final int len) throws IOException {
		this.buffer.write(cbuf, off, len);
	}

	@Override
	public void flush () throws IOException {
		this.buffer.flush();
	}

	@Override
	public void close () throws IOException {
		while (this.stack.size > 0) {
			this.pop();
		}
		this.buffer.close();
	}

	@Override
	public void write (final int c) {
		Err.throwNotImplementedYet();
	}

	@Override
	public void write (final String quoteName) {
		Err.throwNotImplementedYet();
	}

	public StringBuffer getBuffer () {
		return this.buffer;
	}

	@Override
	public JsonString toOutputData () {
		return this.getBuffer().toJsonString();
	}

}
