
package com.jfixby.scarabei.red.string;

import java.io.UnsupportedEncodingException;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Sequence;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.math.IntegerMath;
import com.jfixby.scarabei.api.strings.StringsComponent;

public class RedStrings implements StringsComponent {

	@Override
	public String newString (final ByteArray data) {
		return this.newString(data.toArray());
	}

	@Override
	public String truncated (final String data, final int begin_char, final int end_char) {
		final int beginIndex = (int)IntegerMath.max(begin_char, 0);
		final int endIndex = (int)IntegerMath.min(end_char, data.length());
		return data.substring(beginIndex, endIndex);
	}

	@Override
	public String newString (final char[] chars) {
		return new String(chars);
	}

	@Override
	public String newString (final byte[] bytes) {
		return this.newString(bytes, "UTF-8");
	}

	@Override
	public String newString (final byte[] bytes, final String encoding) {
		try {
			return new String(bytes, encoding);
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String newString (final ByteArray bytes, final String encoding) {
		return this.newString(bytes.toArray(), encoding);
	}

	@Override
	public String replaceAll (String input, final Map<String, String> termsMapping) {
		for (int i = 0; i < termsMapping.size(); i++) {
			input = input.replaceAll(termsMapping.getKeyAt(i), termsMapping.getValueAt(i));
		}
		return input;
	}

	@Override
	public String prefix (final String string, final int offset) {
		final StringBuilder b = new StringBuilder();
		for (int i = 0; i < offset; i++) {
			b.append(string);
		}
		return b.toString();
	}

	@Override
	public String wrapSequence (final Sequence<String> seq, final int size, final String bracketLeft, final String bracketRight,
		final String separator) {
		final int iMax = size - 1;
		if (iMax == -1) {
			return bracketLeft + bracketRight;
		}
		final StringBuilder b = new StringBuilder();
		b.append(bracketLeft);
		for (int i = 0;; i++) {
			b.append(seq.getElementAt(i));
			if (i == iMax) {
				return b.append(bracketRight).toString();
			}
			b.append(separator);
		}
	}

	@Override
	public List<String> split (final String input_string, final String splitter) {
		Debug.checkNull("input_string", input_string);
		Debug.checkNull("splitter", splitter);
		return Collections.newList(input_string.split(splitter));
	}

	@Override
	public String padLeft (final String value, final String pad, final int size) {
		return this.prefix(pad, size - value.length()) + value;
	}

}
