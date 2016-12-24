
package com.jfixby.scarabei.red.log;

import java.io.PrintStream;

import com.jfixby.scarabei.api.log.MESSAGE_MARKER;

public class SimpleLogger extends AbstractLogger {
	@Override
	public String arrayToString (final int indent, final Object[] array) {

		final String canonocal_name = array.getClass().getCanonicalName();
		final int n = array.length;
		if (n == 0) {
			return canonocal_name;
			// return "[]";
		}

		final String canonical = canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]\n";
		// String canonical = "Array";
// final String t = canonical;

		final StringBuilder t = new StringBuilder();
		t.append(canonical);

		final String indent_str = this.indent(indent);
		for (int i = 0; i < n; i++) {
			t.append(indent_str);
			t.append("(" + i + ") ");
			t.append(array[i]);
			t.append("\n");
		}
		return t.toString();
	}

	@Override
	public void logLine (final MESSAGE_MARKER marker, final Object string) {
		PrintStream stream = System.out;
		if (marker == MESSAGE_MARKER.NORMAL) {
			stream = System.out;
		}
		if (marker == MESSAGE_MARKER.ERROR) {
			stream = System.err;
		}
		stream.println(string);
	}

	@Override
	public void logAppend (final MESSAGE_MARKER marker, final Object string) {
		PrintStream stream = System.out;
		if (marker == MESSAGE_MARKER.NORMAL) {
			stream = System.out;
		}
		if (marker == MESSAGE_MARKER.ERROR) {
			stream = System.err;
		}
		stream.print(string);
	}

	@Override
	public String toString (final Object[] array) {
		return this.arrayToString(0, array);
	}

	@Override
	public void logLine (final MESSAGE_MARKER marker) {
		PrintStream stream = System.out;
		if (marker == MESSAGE_MARKER.NORMAL) {
			stream = System.out;
		}
		if (marker == MESSAGE_MARKER.ERROR) {
			stream = System.err;
		}
		stream.println();
	}

	@Override
	public void logAppend (final MESSAGE_MARKER marker) {
		PrintStream stream = System.out;
		if (marker == MESSAGE_MARKER.NORMAL) {
			stream = System.out;
		}
		if (marker == MESSAGE_MARKER.ERROR) {
			stream = System.err;
		}
		stream.println();
	}
}
