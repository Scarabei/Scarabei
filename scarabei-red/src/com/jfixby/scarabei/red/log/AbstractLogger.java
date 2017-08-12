
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
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

public abstract class AbstractLogger implements LoggerComponent {

	abstract public void logLine (final MESSAGE_MARKER marker, String frame, final String line);

// abstract public void logAppend (final MESSAGE_MARKER marker, String frame, final Object string);

	abstract public void logLine (final MESSAGE_MARKER marker, String frame);

// abstract public void logAppend (final MESSAGE_MARKER marker, String frame);

	public abstract String arrayToString (int indent, Object[] array);

	@Override
	public void d (final Object... objects) {
		final String frame = this.getFrame();
		for (int i = 0; i < objects.length; i++) {
			this.logLine(MESSAGE_MARKER.NORMAL, frame, objects[i] + " ");
		}
		this.logLine(MESSAGE_MARKER.NORMAL, frame);
	}

	private String getFrame () {
		if (SystemSettings.component() != null && SystemSettings.getExecutionMode().isBelow(ExecutionMode.TESTING)) {
			return "scarabei";
		}
		final StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		final String aray = Arrays.toString(trace);
// this.log(MESSAGE_MARKER.NORMAL, "", "", Collections.newList(trace));
		String frame = "";
		final String prefix = com.jfixby.scarabei.api.log.Logger.class.getCanonicalName();
		for (int i = 0; i < trace.length; i++) {
			frame = "" + trace[i];
			if (frame.startsWith(prefix)) {
				frame = "" + trace[i + 1];
				break;
			}
		}

		return frame;
	}

	@Override
	public void d (final Object tag, final Object object) {
		final String frame = this.getFrame();
		this.log(MESSAGE_MARKER.NORMAL, frame, tag, object);
	}

	private void log (final MESSAGE_MARKER normal, final String frame) {
		this.logLine(normal, frame);
	}

	private void log (final MESSAGE_MARKER mode, final String frame, final Object tag, final Object object) {

		if (object instanceof Collection) {
			final Collection<?> array = (Collection<?>)object;
			final String canonocal_name = "Collection[]";
			final int n = array.size();
			if (n == 0) {
				this.logLine(mode, frame, canonocal_name);
				return;
			}
			this.logLine(mode, frame, canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]");
			final int indent = (tag + "").length() + 3;
			final String indent_str = this.indent(indent);
			for (int i = 0; i < n; i++) {
				this.logLine(mode, frame, indent_str + "(" + i + ") " + array.getElementAt(i) + "");
			}
			return;

		}
		if (object instanceof java.util.Collection) {
			final java.util.Collection array = (java.util.Collection)object;
			final String canonocal_name = "Collection[]";
			final int n = array.size();
			if (n == 0) {
				this.logLine(mode, frame, canonocal_name);
				return;
			}
			this.logLine(mode, frame, canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]");
			final int indent = (tag + "").length() + 3;
			final String indent_str = this.indent(indent);
			int i = 0;
			for (final Object e : array) {
				this.logLine(mode, frame, indent_str + "(" + i + ") " + e + "");
				i++;
			}
			return;

		}
		if (object instanceof Mapping) {
			final Mapping array = (Mapping)object;
			final String canonocal_name = "Collection[]";
			final int n = array.size();
			if (n == 0) {
				this.logLine(mode, frame, canonocal_name);
				return;
			}
			this.logLine(mode, frame, canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]");
			final int indent = (tag + "").length() + 3;
			final String indent_str = this.indent(indent);
			int i = 0;
			for (final Object e : array.keys()) {
// this.logLine(normal, frame, indent_str + "(" + i + ") " + e + "");
				this.logLine(mode, frame, indent_str + "(" + i + ") " + array.getKeyAt(i) + " :-> " + array.getValueAt(i) + "");
				i++;
			}
			return;

		}
		if (object instanceof java.util.Map) {
			final java.util.Map array = (java.util.Map)object;
			final String canonocal_name = "Collection[]";
			final int n = array.size();
			if (n == 0) {
				this.logLine(mode, frame, canonocal_name);
				return;
			}
			this.logLine(mode, frame, canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]");
			final int indent = (tag + "").length() + 3;
			final String indent_str = this.indent(indent);
			int i = 0;
			for (final Object e : array.keySet()) {
				this.logLine(mode, frame, indent_str + "(" + i + ") " + e + " :-> " + array.get(e) + "");
				i++;
			}
			return;

		}

		this.logLine(mode, frame, tag + " > " + object);
	}

	private void log (final MESSAGE_MARKER normal, final String frame, final Object tag) {
		this.logLine(normal, frame, tag + "");
	}

	@Override
	public void d (final Object tag) {
		final String frame = this.getFrame();
		this.log(MESSAGE_MARKER.NORMAL, frame, tag);
	}

	@Override
	public void e (final Object tag, final Object object) {
		final String frame = this.getFrame();
		this.log(MESSAGE_MARKER.ERROR, frame, tag, object);
	}

	@Override
	public void e (final Object tag) {
		final String frame = this.getFrame();
		this.log(MESSAGE_MARKER.ERROR, frame, tag);
	}

	@Override
	public void d () {
		final String frame = this.getFrame();
		this.log(MESSAGE_MARKER.NORMAL, frame);
	}

	@Override
	public void e () {
		final String frame = this.getFrame();
		this.log(MESSAGE_MARKER.ERROR, frame);
	}

	@Override
	public void e (final Object tag, final Throwable err) {
		final String frame = this.getFrame();
		this.log(MESSAGE_MARKER.ERROR, frame, tag);
		this.log(MESSAGE_MARKER.ERROR, frame, err);
	}

	@Override
	public void e (final Throwable err) {
		final String frame = this.getFrame();
		this.log(MESSAGE_MARKER.ERROR, frame, this.stackTraceToString(err));
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

		string.append(canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]");
		final String indent_str = this.indent(indent);
		for (int i = 0; i < n; i++) {
			string.append(indent_str + "(" + i + ") " + array.getElementAt(i) + "");
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

		string.append(canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]");
		final String indent_str = this.indent(indent);
		int i = 0;
		for (final Object e : array) {
// int i = 0; i < n; i++
			string.append(indent_str + "(" + i + ") " + e + "");
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

		string.append(canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]");
		final String indent_str = this.indent(indent);
		for (int i = 0; i < n; i++) {
			string.append(indent_str + "(" + i + ") " + array.getKeyAt(i) + " :-> " + array.getValueAt(i) + "");
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

		string.append(canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]");
		final String indent_str = this.indent(indent);
		int i = 0;
		for (final Iterator<?> I = array.keySet().iterator(); I.hasNext();) {
			final Object key = I.next();
			final Object value = array.get(key);
			string.append(indent_str + "(" + i + ") " + key + " :-> " + value + "");
			i++;
		}

		return string.toString();
	}

	public String indent (final int indent) {
		final StringBuilder r = new StringBuilder();
		for (int i = 0; i < indent; i++) {
			r.append(" ");
		}
		return r.toString();
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

// public void d_appendChars (final Object msg) {
// this.logAppend(MESSAGE_MARKER.NORMAL, msg);
// }

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
//
// public void d (final String string, final Object... objects_list) {
// this.logLine(MESSAGE_MARKER.NORMAL, string + " > " + this.buld_list(objects_list));
//
// }

}
