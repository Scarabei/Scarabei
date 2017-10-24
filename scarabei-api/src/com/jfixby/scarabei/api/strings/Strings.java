
package com.jfixby.scarabei.api.strings;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Sequence;
import com.jfixby.scarabei.api.java.ByteArray;

public class Strings {

	static private ComponentInstaller<StringsComponent> componentInstaller = new ComponentInstaller<StringsComponent>("Strings");

	public static final void installComponent (final StringsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final StringsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final StringsComponent component () {
		return componentInstaller.getComponent();
	}

	public static List<String> split (final String input_string, final String splitter) {
		return invoke().split(input_string, splitter);
	}

	public static String newString (final ByteArray data) {
		return invoke().newString(data);
	}

	public static String truncated (final String data, final int begin_char, final int end_char) {
		return invoke().truncated(data, begin_char, end_char);
	}

	public static String newString (final char[] chars) {
		return invoke().newString(chars);
	}

	public static String newString (final byte[] bytes) {
		return invoke().newString(bytes);
	}

	public static String newString (final byte[] bytes, final String encoding) {
		return invoke().newString(bytes, encoding);
	}

	public static String newString (final ByteArray bytes, final String encoding) {
		return invoke().newString(bytes, encoding);
	}

	public static String replaceAll (final String input, final Map<String, String> termsMapping) {
		return invoke().replaceAll(input, termsMapping);
	}

	public static String prefix (final String string, final int offset) {
		return invoke().prefix(string, offset);
	}

	public static String wrapSequence (final Sequence<String> seq, final int size, final String bracketLeft,
		final String bracketRight, final String separator) {
		return invoke().wrapSequence(seq, size, bracketLeft, bracketRight, separator);
	}

	public static String padLeft (final String value, final String pad, final int size) {
		return invoke().padLeft(value, pad, size);
	}

}
