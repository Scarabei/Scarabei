package com.jfixby.red.desktop.log;

import com.jfixby.red.log.AlpaeroLogger;


public class DesktopLogger extends AlpaeroLogger {
	@Override
	public String arrayToString(int indent, Object[] array) {

		String canonocal_name = array.getClass().getCanonicalName();
		final int n = array.length;
		if (n == 0) {
			return canonocal_name;
			// return "[]";
		}

		String canonical = canonocal_name.substring(0,
				canonocal_name.length() - 1) + n + "]\n";
		// String canonical = "Array";
		String t = canonical;
		String indent_str = indent(indent);
		for (int i = 0; i < n; i++) {
			t = t + indent_str + "(" + i + ") " + array[i] + "\n";
		}
		return t;
	}

	@Override
	public void System_err_println(Object string) {
		System.err.println(string);
	}

	@Override
	public void System_out_println(Object string) {
		System.out.println(string);
	}

	@Override
	public void System_err_println() {
		System.err.println();
	}

	@Override
	public void System_out_println() {
		System.out.println();
	}

	@Override
	public void System_out_print(Object string) {
		System.out.print(string);
	}

	@Override
	public String toString(Object[] array) {
		return this.arrayToString(0, array);
	}
}
