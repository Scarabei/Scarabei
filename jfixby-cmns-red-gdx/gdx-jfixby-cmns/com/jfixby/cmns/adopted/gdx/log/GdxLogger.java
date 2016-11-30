
package com.jfixby.cmns.adopted.gdx.log;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.log.LoggerComponent;
import com.jfixby.cmns.api.util.JUtils;

public class GdxLogger implements LoggerComponent {

	public GdxLogger () {
		super();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	@Override
	public void d (final Object... objects) {
		for (int i = 0; i < objects.length; i++) {
			this.System_out_print(objects[i] + " ");
		}
		this.System_out_println();
	}

	@Override
	public void d (final Object string, final Object object) {
		final String tag = string + "";
		if (object instanceof Object[]) {
			this.System_out_println(string + " > " + this.arrayToString(tag.length() + 3, (Object[])object));
			return;
		}
		if (object instanceof byte[]) {
			this.System_out_println(string + " > " + JUtils.newString((byte[])object));
			return;
		}
		if (object instanceof EditableCollection) {
			this.System_out_println(string + " > " + this.listToString(tag.length() + 3, (EditableCollection<?>)object));
			return;
		}
		if (object instanceof Map) {
			this.System_out_println(string + " > " + this.mapToString(tag.length() + 3, (Map<?, ?>)object));
			return;
		}
		this.System_out_println(string + " > " + object);
	}

	private String listToString (final int indent, final EditableCollection<?> array) {
		final String canonocal_name = "Collection[]";
		final int n = array.size();
		if (n == 0) {
			return canonocal_name;
		}

		String t = canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]\n";
		final String indent_str = this.indent(indent);
		for (int i = 0; i < n; i++) {
			t = t + indent_str + "(" + i + ") " + array.getElementAt(i) + "\n";
		}
		return t;
	}

	private String mapToString (final int indent, final Map<?, ?> array) {
		final String canonocal_name = "Map[]";
		final int n = array.size();
		if (n == 0) {
			return canonocal_name;
		}

		String t = canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]\n";
		final String indent_str = this.indent(indent);
		for (int i = 0; i < n; i++) {
			t = t + indent_str + "(" + i + ") " + array.getKeyAt(i) + ":->" + array.getValueAt(i) + "\n";
		}

		// d("---Map[" + name + "]-----------------------");
		// for (Iterator<?> i = m.keySet().iterator(); i.hasNext();) {
		// Object key = i.next();
		// Object value = m.get(key);
		// d(" " + key + " = " + value);
		// }
		// d("---End----------------------------");

		return t;
	}

	public String indent (final int indent) {
		String r = "";
		for (int i = 0; i < indent; i++) {
			r = r + " ";
		}
		return r;
	}

	@Override
	public void d (final Object string) {
		this.System_out_println(string);
	}

	@Override
	public void e (final Object string, final Object object) {
		this.System_err_println(string + " > " + object);
	}

	@Override
	public void e (final Object string) {
		this.System_err_println(string);
	}

	public void d (final String string, final Object... objects_list) {
		this.System_out_println(string + " > " + this.buld_list(objects_list));

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

	private final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

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
		this.System_out_println();
	}

	@Override
	public void e () {
		this.System_err_println();
	}

	@Override
	public void d_addChars (final Object msg) {
		this.System_out_print(msg);
	}

	public String arrayToString (final int indent, final Object[] array) {

		final String canonocal_name = "Object[]";
		final int n = array.length;
		if (n == 0) {
			return canonocal_name;
			// return "[]";
		}

		final String canonical = canonocal_name.substring(0, canonocal_name.length() - 1) + n + "]\n";
		// String canonical = "Array";
		String t = canonical;
		final String indent_str = this.indent(indent);
		for (int i = 0; i < n; i++) {
			t = t + indent_str + "(" + i + ") " + array[i] + "\n";
		}
		return t;
	}

	public void System_err_println (final Object string) {
		Gdx.app.error("G", string + "");
	}

	public void System_out_println (final Object string) {
		Gdx.app.log("G", string + "");
	}

	public void System_err_println () {
		Gdx.app.error("G", "");
	}

	public void System_out_println () {
		Gdx.app.log("G", "");
	}

	public void System_out_print (final Object string) {
		Gdx.app.log("G Char:", "" + string);
	}

	@Override
	public String toString (final Object[] array) {
		return this.arrayToString(0, array);
	}

	@Override
	public String stackTraceToString (final Throwable e) {
		Err.reportNotImplementedYet();
		return null;
	}

	@Override
	public String throwableToString (final Throwable aThrowable) {
		Err.reportNotImplementedYet();
		return null;
	}

}
