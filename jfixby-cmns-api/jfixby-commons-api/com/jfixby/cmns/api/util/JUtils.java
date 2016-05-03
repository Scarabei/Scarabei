
package com.jfixby.cmns.api.util;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.java.ByteArray;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.MountPoint;
import com.jfixby.cmns.api.util.path.RelativePath;

public class JUtils {

	static private ComponentInstaller<UtilsComponent> componentInstaller = new ComponentInstaller<UtilsComponent>("JUtils");

	public static final void installComponent (final UtilsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final UtilsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final UtilsComponent component () {
		return componentInstaller.getComponent();
	}

	public static final RelativePath newRelativePath (final String path_string) {
		return invoke().newRelativePath(path_string);
	}

	public static final <T extends MountPoint> AbsolutePath<T> newAbsolutePath (final T mount_point, final RelativePath relative) {
		return invoke().newAbsolutePath(mount_point, relative);
	}

	public static final <T extends MountPoint> AbsolutePath<T> newAbsolutePath (final T mount_point) {
		return invoke().newAbsolutePath(mount_point);
	}

	public static final RelativePath newRelativePath (final List<String> steps_list) {
		return invoke().newRelativePath(steps_list);
	}

	public static final RelativePath newRelativePath (final java.util.List<String> steps_list) {
		return invoke().newRelativePath(steps_list);
	}

	public static final RelativePath newRelativePath () {
		return invoke().newRelativePath();
	}

	public static <T> StateSwitcher<T> newStateSwitcher (final T default_state) {
		return invoke().newStateSwitcher(default_state);
	}

	public static List<String> split (final String input_string, final String splitter) {
		return invoke().split(input_string, splitter);
	}

	public static String newString (final ByteArray data) {
		return invoke().newString(data);
	}

	public static <T> Set<T> intersectCollection (final Collection<T> listA, final Collection<T> listB) {
		return invoke().intersectCollections(listA, listB);
	}

	public static String truncated (final String data, final int begin_char, final int end_char) {
		return invoke().truncated(data, begin_char, end_char);
	}

	public static boolean equalObjects (final Object a, final Object b) {
		return invoke().equalObjects(a, b);
	}

	public static BinaryCode binaryCodeOf (final int bits, final int numberOfBits) {
		return invoke().binaryCodeOf(bits, numberOfBits);
	}

	public static EditableBinaryCode newBinaryCode () {
		return invoke().newBinaryCode();
	}

	public static ByteArray newByteArray (final int size) {
		return invoke().newByteArray(size);
	}

	public static ByteArray newByteArray (final byte[] bytes) {
		return invoke().newByteArray(bytes);
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

}
