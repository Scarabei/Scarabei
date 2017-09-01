
package com.jfixby.scarabei.api.util;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.taskman.SimpleProgress;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.MountPoint;
import com.jfixby.scarabei.api.util.path.RelativePath;

public class Utils {

	static private ComponentInstaller<UtilsComponent> componentInstaller = new ComponentInstaller<UtilsComponent>("JUtils");

	public static final void installComponent (final UtilsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
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

	public static final RelativePath newRelativePath (final Collection<String> steps_list) {
		return invoke().newRelativePath(steps_list);
	}

	public static final RelativePath newRelativePath (final java.util.List<String> steps_list) {
		return invoke().newRelativePath(steps_list);
	}

	public static final RelativePath newRelativePath () {
		return invoke().newRelativePath();
	}

	public static <T> Set<T> intersectCollection (final Collection<T> listA, final Collection<T> listB) {
		return invoke().intersectCollections(listA, listB);
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

	public static ProgressIndicator newProgressIndicator () {
		return invoke().newProgressIndicator();
	}

	public static SimpleProgress newSimpleProgress () {
		return invoke().newSimpleProgress();
	}

}
