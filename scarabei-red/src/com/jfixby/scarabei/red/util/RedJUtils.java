
package com.jfixby.scarabei.red.util;

import java.io.UnsupportedEncodingException;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.Names;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.collections.Sequence;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.lang.RedByteArray;
import com.jfixby.scarabei.api.math.IntegerMath;
import com.jfixby.scarabei.api.util.BinaryCode;
import com.jfixby.scarabei.api.util.EditableBinaryCode;
import com.jfixby.scarabei.api.util.ProgressIndicator;
import com.jfixby.scarabei.api.util.StateSwitcher;
import com.jfixby.scarabei.api.util.UtilsComponent;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.MountPoint;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class RedJUtils implements UtilsComponent {

	@Override
	public List<String> split (final String input_string, final String splitter) {
		Debug.checkNull("input_string", input_string);
		Debug.checkNull("splitter", splitter);
		return Collections.newList(input_string.split(splitter));
	}

	@Override
	public <T extends MountPoint> AbsolutePath<T> newAbsolutePath (final T mount_point) {
		return new RedAbsolutePath<T>(mount_point, new RedRelativePath(RedRelativePath.E));
	}

	@Override
	public RelativePath newRelativePath () {
		return new RedRelativePath(RedRelativePath.E);
	}

	@Override
	public RelativePath newRelativePath (final String path_string) {
		return new RedRelativePath(path_string);
	}

	@Override
	public <T extends MountPoint> AbsolutePath<T> newAbsolutePath (final T mount_point, final RelativePath relative) {
		return new RedAbsolutePath<T>(mount_point, relative);
	}

	@Override
	public RelativePath newRelativePath (final Collection<String> steps_list) {
		return new RedRelativePath(steps_list);
	}

	@Override
	public <T> StateSwitcher<T> newStateSwitcher (final T default_state) {
		return new RedStateSwitcher<T>(default_state);
	}

	@Override
	public RelativePath newRelativePath (final java.util.List<String> steps_list) {
		return this.newRelativePath(Collections.newList(steps_list));
	}

	@Override
	public String newString (final ByteArray data) {
		return this.newString(data.toArray());
	}

	@Override
	public <T> Set<T> intersectCollections (final Collection<T> listA, final Collection<T> listB) {
		final Set<T> intersection = Collections.newSet();
		for (int i = 0; i < listA.size(); i++) {
			final T a = listA.getElementAt(i);
			if (listB.contains(a)) {
				intersection.add(a);
			}
		}
		for (int i = 0; i < listB.size(); i++) {
			final T b = listB.getElementAt(i);
			if (listA.contains(b)) {
				intersection.add(b);
			}
		}
		return intersection;
	}

	@Override
	public String truncated (final String data, final int begin_char, final int end_char) {
		final int beginIndex = (int)IntegerMath.max(begin_char, 0);
		final int endIndex = (int)IntegerMath.min(end_char, data.length());
		return data.substring(beginIndex, endIndex);
	}

	@Override
	public boolean equalObjects (final Object a, final Object b) {
		if (a == b) {
			return true;
		}
		if (a == null) {
			return false;
		}
		if (b == null) {
			return false;
		}
		return a.equals(b);
	}

	final RedBinaryCodeCache cache = new RedBinaryCodeCache();

	@Override
	public BinaryCode binaryCodeOf (final int bits, final int numberOfBits) {
		if (numberOfBits <= 8) {
			return this.cache.get(bits, numberOfBits);
		}
		return this.newBinaryCode().append(bits, numberOfBits);
	}

	@Override
	public EditableBinaryCode newBinaryCode () {
		return new RedBinaryCode();
	}

	@Override
	public ByteArray newByteArray (final int size) {
		return new RedByteArray(size);
	}

	@Override
	public ByteArray newByteArray (final byte[] bytes) {
		return new RedByteArray(bytes);
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
	public ProgressIndicator newProgressIndicator () {
		return new RedProgressIndicator();
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

	private ID NULL_NAME;

	@Override
	public ID nameOf (final Class<?> type) {
		if (type == null) {
			if (this.NULL_NAME == null) {
				this.NULL_NAME = Names.newID("null");
			}
			return this.NULL_NAME;
		}
		return Names.newID(type.getCanonicalName());
	}

}
