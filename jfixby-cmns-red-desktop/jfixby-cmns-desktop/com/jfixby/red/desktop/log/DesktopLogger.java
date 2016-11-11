
package com.jfixby.red.desktop.log;

import com.jfixby.red.log.RedLogger;

public class DesktopLogger extends RedLogger {
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
	public void System_err_println (final Object string) {
		System.err.println(string);
	}

	@Override
	public void System_out_println (final Object string) {
		System.out.println(string);
	}

	@Override
	public void System_err_println () {
		System.err.println();
	}

	@Override
	public void System_out_println () {
		System.out.println();
	}

	@Override
	public void System_out_print (final Object string) {
		System.out.print(string);
	}

	@Override
	public String toString (final Object[] array) {
		return this.arrayToString(0, array);
	}
}
