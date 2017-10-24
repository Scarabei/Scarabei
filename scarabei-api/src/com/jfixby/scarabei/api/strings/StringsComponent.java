
package com.jfixby.scarabei.api.strings;

import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Sequence;
import com.jfixby.scarabei.api.java.ByteArray;

public interface StringsComponent {

	List<String> split (String input_string, String splitter);

	String newString (ByteArray data);

	String truncated (String data, int begin_char, int end_char);

	String newString (char[] chars);

	String newString (byte[] bytes);

	String newString (byte[] bytes, String encoding);

	String newString (ByteArray bytes, String encoding);

	String replaceAll (String input, Map<String, String> termsMapping);

	String prefix (String string, int offset);

	String wrapSequence (Sequence<String> seq, int size, String bracketLeft, String bracketRight, final String separator);

	String padLeft (String value, String pad, int size);

}
