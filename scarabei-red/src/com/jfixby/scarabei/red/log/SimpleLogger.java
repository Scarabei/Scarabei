
package com.jfixby.scarabei.red.log;

import java.io.PrintStream;

import com.jfixby.scarabei.api.log.MESSAGE_MARKER;
import com.jfixby.scarabei.api.strings.Strings;
import com.jfixby.scarabei.api.sys.settings.ExecutionMode;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

public class SimpleLogger extends AbstractLogger {

	public int pad = 100;

	@Override
	public void logLine (final MESSAGE_MARKER marker, final String frame) {
		PrintStream stream = System.out;
		if (marker == MESSAGE_MARKER.NORMAL) {
			stream = System.out;
		}
		if (marker == MESSAGE_MARKER.ERROR) {
			stream = System.err;
		}

		this.prinOut(stream, frame, "");
	}

	private void prinOut (final PrintStream stream, final String frame, final Object data) {
		final String padded;
		if (SystemSettings.component() != null && SystemSettings.getExecutionMode().isBelow(ExecutionMode.TESTING)) {
			this.pad = 0;
			padded = "";
		} else {
			padded = this.indent(Math.max(0, this.pad - frame.length())) + frame + "| ";
		}
		stream.println(padded + data);
	}

	@Override
	public void logLine (final MESSAGE_MARKER marker, final String frame, final String string) {
		PrintStream stream = System.out;
		if (marker == MESSAGE_MARKER.NORMAL) {
			stream = System.out;
		}
		if (marker == MESSAGE_MARKER.ERROR) {
			stream = System.err;
		}
		this.prinOut(stream, frame, string);
	}

	@Override
	public String arrayToString (final int indent, final Object[] array) {

		final String canonocal_name = array.getClass().getCanonicalName();
		final int n = array.length;
		if (n == 0) {
			return canonocal_name;
			// return "[]";
		}

		final String canonical = canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]";
		// String canonical = "Array";
// final String t = canonical;

		final StringBuilder t = new StringBuilder();
		t.append(canonical);

		final int maxLen = ("" + n).length();
		final String indent_str = this.indent(indent);
		for (int i = 0; i < n; i++) {
			t.append(indent_str);
			final int iLen = ("" + i).length();
			final String prefix = Strings.prefix(" ", maxLen - iLen);
			t.append(prefix);
			t.append("(" + i + ") ");
			t.append(array[i]);
			t.append("\n");
		}
		return t.toString();
	}

// @Override
// public void logAppend (final MESSAGE_MARKER marker, final String frame, final Object string) {
// PrintStream stream = System.out;
// if (marker == MESSAGE_MARKER.NORMAL) {
// stream = System.out;
// }
// if (marker == MESSAGE_MARKER.ERROR) {
// stream = System.err;
// }
// stream.print(string);
// }

	@Override
	public String toString (final Object[] array) {
		return this.arrayToString(0, array);
	}

// @Override
// public void logAppend (final MESSAGE_MARKER marker, final String frame) {
// PrintStream stream = System.out;
// if (marker == MESSAGE_MARKER.NORMAL) {
// stream = System.out;
// }
// if (marker == MESSAGE_MARKER.ERROR) {
// stream = System.err;
// }
// stream.println();
// }
}
