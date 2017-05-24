
package com.jfixby.scarabei.red.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Mapping;
import com.jfixby.scarabei.api.log.LoggerComponent;
import com.jfixby.scarabei.api.log.MESSAGE_MARKER;

public abstract class AbstractLogger implements LoggerComponent {

	abstract public void logLine (final MESSAGE_MARKER marker, final Object string);

	abstract public void logAppend (final MESSAGE_MARKER marker, final Object string);

	abstract public void logLine (final MESSAGE_MARKER marker);

	abstract public void logAppend (final MESSAGE_MARKER marker);

	public abstract String arrayToString (int indent, Object[] array);

	@Override
	public void d (final Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			this.logLine(MESSAGE_MARKER.NORMAL, objects[i] + " ");
		}
		this.logLine(MESSAGE_MARKER.NORMAL);
	}

	@Override
	public void d (final Object string, final Object object) {
		final String tag = string + "";
		this.logLine(MESSAGE_MARKER.NORMAL, string + " > " + this.toString(tag.length() + 3, object));
	}

	private String toString (final int indent, final Object object) {

		if (object instanceof byte[]) {
			return this.toHexString((byte[])object);

		}
		if (object instanceof int[]) {
			return Arrays.toString((int[])object);
		}
		if (object instanceof float[]) {
			return Arrays.toString((float[])object);
		}

		if (object instanceof Object[]) {
			return this.arrayToString(indent, (Object[])object);

		}

		if (object instanceof Collection) {
			return this.listToString(indent, (Collection<?>)object);

		}
		if (object instanceof java.util.Collection) {
			return this.listToString(indent, (java.util.Collection<?>)object);

		}
		if (object instanceof Mapping) {
			return this.mapToString(indent, (Mapping<?, ?>)object);

		}
		if (object instanceof java.util.Map) {
			return this.mapToString(indent, (java.util.Map<?, ?>)object);

		}

		return "" + object;
	}

	private String listToString (final int indent, final Collection<?> array) {
		final StringBuilder string = new StringBuilder();
		final String canonocal_name = "Collection[]";
		final int n = array.size();
		if (n == 0) {
			return canonocal_name;
		}

		string.append(canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]\n");
		final String indent_str = this.indent(indent);
		for (int i = 0; i < n; i++) {
			string.append(indent_str + "(" + i + ") " + array.getElementAt(i) + "\n");
		}
		return string.toString();
	}

	private String listToString (final int indent, final java.util.Collection<?> array) {
		final StringBuilder string = new StringBuilder();
		final String canonocal_name = "Collection[]";
		final int n = array.size();
		if (n == 0) {
			return canonocal_name;
		}

		string.append(canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]\n");
		final String indent_str = this.indent(indent);
		int i = 0;
		for (final Object e : array) {
// int i = 0; i < n; i++
			string.append(indent_str + "(" + i + ") " + e + "\n");
			i++;
		}
		return string.toString();
	}

	private String mapToString (final int indent, final Mapping<?, ?> array) {
		final StringBuilder string = new StringBuilder();
		final String canonocal_name = "Map[]";
		final int n = array.size();
		if (n == 0) {
			return canonocal_name;
		}

		string.append(canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]\n");
		final String indent_str = this.indent(indent);
		for (int i = 0; i < n; i++) {
			string.append(indent_str + "(" + i + ") " + array.getKeyAt(i) + " :-> " + array.getValueAt(i) + "\n");
		}

		return string.toString();
	}

	private String mapToString (final int indent, final java.util.Map<?, ?> array) {
		final StringBuilder string = new StringBuilder();
		final String canonocal_name = "Map[]";
		final int n = array.size();
		if (n == 0) {
			return canonocal_name;
		}

		string.append(canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]\n");
		final String indent_str = this.indent(indent);
		int i = 0;
		for (final Iterator<?> I = array.keySet().iterator(); I.hasNext();) {
			final Object key = I.next();
			final Object value = array.get(key);
			string.append(indent_str + "(" + i + ") " + key + " :-> " + value + "\n");
			i++;
		}

		return string.toString();
	}

	public String indent (final int indent) {
		String r = "";
		for (int i = 0; i < indent; i++) {
			r = r + " ";
		}
		return r;
	}

	@Override
	public void d (final Object object) {
		this.logLine(MESSAGE_MARKER.NORMAL, this.toString(0, object));
	}

	@Override
	public void e (final Object string, final Object object) {
		this.logLine(MESSAGE_MARKER.ERROR, string + " > " + this.toString(0, object));
	}

	@Override
	public void e (final Object object) {
		this.logLine(MESSAGE_MARKER.ERROR, this.toString(0, object));
	}

	public void d (final String string, final Object... objects_list) {
		this.logLine(MESSAGE_MARKER.NORMAL, string + " > " + this.buld_list(objects_list));

	}

	private String buld_list (final Object[] objects_list) {
		String result = "";
		for (int i = 0; i < objects_list.length; i++) {
			if (i == 0) {
				result = result + objects_list[i];
			} else {
				result = result + ", " + objects_list[i];
			}
		}
		return result;
	}

	private final char[] HEXDIGITS = "0123456789abcdef".toUpperCase().toCharArray();

	public String toHexString (final byte[] bytes) {
		final StringBuilder sb = new StringBuilder(bytes.length * 3);
		for (int b : bytes) {
			b &= 0xff;
			sb.append(this.HEXDIGITS[b >> 4]);
			sb.append(this.HEXDIGITS[b & 15]);
			sb.append(' ');
		}
		return "(" + bytes.length + ") " + sb.toString();
	}

	@Override
	public void d () {
		this.logLine(MESSAGE_MARKER.NORMAL);
	}

	@Override
	public void e () {
		this.logLine(MESSAGE_MARKER.ERROR);
	}

	@Override
	public void d_appendChars (final Object msg) {
		this.logAppend(MESSAGE_MARKER.NORMAL, msg);
	}

	public final static String SEPARATOR = System.getProperty("line.separator");

	@Override
	public String stackTraceToString (final Throwable e) {
		// final StackTraceElement[] arr = e.getStackTrace();
		final StringBuilder report = new StringBuilder();
		report.append(this.throwableToString(e)).append(SEPARATOR);
		final Throwable cause = e.getCause();
		if (cause != null) {
			report.append(this.throwableToString(cause));
		}
		return report.toString();
	}

	public String throwableToString (final Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}

	@Override
	public void e (final Object tag, final Throwable err) {
		this.e(tag);
		this.e(this.throwableToString(err));
	}

	@Override
	public void e (final Throwable err) {
		this.e(this.throwableToString(err));
	}

}
