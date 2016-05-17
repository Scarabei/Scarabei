
package com.jfixby.red.log;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Mapping;
import com.jfixby.cmns.api.log.LoggerComponent;
import com.jfixby.cmns.api.util.JUtils;

public abstract class AlpaeroLogger implements LoggerComponent {

	public abstract String arrayToString (int indent, Object[] array);

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
		if (object instanceof Collection) {
			this.System_out_println(string + " > " + this.listToString(tag.length() + 3, (Collection<?>)object));
			return;
		}
		if (object instanceof Mapping) {
			this.System_out_println(string + " > " + this.mapToString(tag.length() + 3, (Mapping<?, ?>)object));
			return;
		}
		this.System_out_println(string + " > " + object);
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

		// d("---Map[" + name + "]-----------------------");
		// for (Iterator<?> i = m.keySet().iterator(); i.hasNext();) {
		// Object key = i.next();
		// Object value = m.get(key);
		// d(" " + key + " = " + value);
		// }
		// d("---End----------------------------");

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

	public abstract void System_err_println (Object string);

	public abstract void System_out_println (Object string);

	public abstract void System_err_println ();

	public abstract void System_out_println ();

	public abstract void System_out_print (Object string);

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
}
