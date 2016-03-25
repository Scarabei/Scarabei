package com.jfixby.cmns.api.util;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.MountPoint;
import com.jfixby.cmns.api.util.path.RelativePath;

public class JUtils {

    static private ComponentInstaller<UtilsComponent> componentInstaller = new ComponentInstaller<UtilsComponent>(
	    "JUtils");

    public static final void installComponent(UtilsComponent component_to_install) {
	componentInstaller.installComponent(component_to_install);
    }

    public static final UtilsComponent invoke() {
	return componentInstaller.invokeComponent();
    }

    public static final UtilsComponent component() {
	return componentInstaller.getComponent();
    }

    public static final RelativePath newRelativePath(String path_string) {
	return invoke().newRelativePath(path_string);
    }

    public static final <T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point, RelativePath relative) {
	return invoke().newAbsolutePath(mount_point, relative);
    }

    public static final <T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point) {
	return invoke().newAbsolutePath(mount_point);
    }

    public static final RelativePath newRelativePath(List<String> steps_list) {
	return invoke().newRelativePath(steps_list);
    }

    public static final RelativePath newRelativePath(java.util.List<String> steps_list) {
	return invoke().newRelativePath(steps_list);
    }

    public static final RelativePath newRelativePath() {
	return invoke().newRelativePath();
    }

    public static <T> StateSwitcher<T> newStateSwitcher(T default_state) {
	return invoke().newStateSwitcher(default_state);
    }

    public static List<String> split(String input_string, String splitter) {
	return invoke().split(input_string, splitter);
    }

    public static String newString(byte[] data) {
	return invoke().newString(data);
    }

    public static <T> Set<T> intersectCollection(Collection<T> listA, Collection<T> listB) {
	return invoke().intersectCollections(listA, listB);
    }

    public static String truncated(String data, int begin_char, int end_char) {
	return invoke().truncated(data, begin_char, end_char);
    }

    public static boolean equalObjects(final Object a, final Object b) {
	return invoke().equalObjects(a, b);
    }

    public static BitForm bitformOf(final int bits, final int numberOfBits) {
	return invoke().bitformOf(bits, numberOfBits);
    }

    public static BitForm newBitForm() {
	return invoke().newBitForm();
    }

}
