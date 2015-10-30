package com.jfixby.cmns.adopted.gdx.log;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.log.LoggerComponent;

public class GdxLogger implements LoggerComponent {

	public GdxLogger() {
		super();
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}

	public void d(String string, Object object) {
		if (object instanceof Object[]) {
			System_out_println(string + " > "
					+ arrayToString(string.length() + 3, (Object[]) object));
			return;
		}
		if (object instanceof byte[]) {
			System_out_println(string + " > " + new String((byte[]) object));
			return;
		}
		if (object instanceof EditableCollection) {
			System_out_println(string + " > "
					+ listToString(string.length() + 3, (EditableCollection<?>) object));
			return;
		}
		if (object instanceof Map) {
			System_out_println(string + " > "
					+ mapToString(string.length() + 3, (Map<?, ?>) object));
			return;
		}
		System_out_println(string + " > " + object);
	}

	private String listToString(int indent, EditableCollection<?> array) {
		String canonocal_name = "Collection[]";
		final int n = array.size();
		if (n == 0) {
			return canonocal_name;
		}

		String t = canonocal_name.substring(0, canonocal_name.length() - 1) + n
				+ "]\n";
		String indent_str = indent(indent);
		for (int i = 0; i < n; i++) {
			t = t + indent_str + "(" + i + ") " + array.getElementAt(i) + "\n";
		}
		return t;
	}

	private String mapToString(int indent, Map<?, ?> array) {
		String canonocal_name = "Map[]";
		final int n = array.size();
		if (n == 0) {
			return canonocal_name;
		}

		String t = canonocal_name.substring(0, canonocal_name.length() - 1) + n
				+ "]\n";
		String indent_str = indent(indent);
		for (int i = 0; i < n; i++) {
			t = t + indent_str + "(" + i + ") " + array.getKeyAt(i) + ":->"
					+ array.getValueAt(i) + "\n";
		}

		// d("---Map[" + name + "]-----------------------");
		// for (Iterator<?> i = m.keySet().iterator(); i.hasNext();) {
		// Object key = i.next();
		// Object value = m.get(key);
		// d("   " + key + " = " + value);
		// }
		// d("---End----------------------------");

		return t;
	}

	public String indent(int indent) {
		String r = "";
		for (int i = 0; i < indent; i++) {
			r = r + " ";
		}
		return r;
	}

	public void d(Object string) {
		System_out_println(string);
	}

	public void e(String string, Object object) {
		System_err_println(string + " > " + object);
	}

	public void e(Object string) {
		System_err_println(string);
	}

	public void d(String string, Object... objects_list) {
		System_out_println(string + " > " + buld_list(objects_list));

	}

	private String buld_list(Object[] objects_list) {
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

	public String toHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 3);
		for (int b : bytes) {
			b &= 0xff;
			sb.append(HEXDIGITS[b >> 4]);
			sb.append(HEXDIGITS[b & 15]);
			sb.append(' ');
		}
		return "(" + bytes.length + ") " + sb.toString();
	}

	@Override
	public void d() {
		System_out_println();
	}

	@Override
	public void e() {
		System_err_println();
	}

	@Override
	public void d_addChars(Object msg) {
		System_out_print(msg);
	}

	public String arrayToString(int indent, Object[] array) {

		String canonocal_name = "Object[]";
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

	public void System_err_println(Object string) {
		Gdx.app.error("G", string + "");
	}

	public void System_out_println(Object string) {
		Gdx.app.log("G", string + "");
	}

	public void System_err_println() {
		Gdx.app.error("G", "");
	}

	public void System_out_println() {
		Gdx.app.log("G", "");
	}

	public void System_out_print(Object string) {
		Gdx.app.log("G Char:", "" + string);
	}

	@Override
	public String toString(Object[] array) {
		return this.arrayToString(0, array);
	}

}
