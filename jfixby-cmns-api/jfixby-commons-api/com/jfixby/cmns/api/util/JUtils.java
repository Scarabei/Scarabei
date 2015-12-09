package com.jfixby.cmns.api.util;

import com.jfixby.cmns.api.ComponentInstaller;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.cmns.api.path.MountPoint;
import com.jfixby.cmns.api.path.RelativePath;

public class JUtils {

	static private ComponentInstaller<UtilsComponent> componentInstaller = new ComponentInstaller<UtilsComponent>("JUtils");

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

	public static final RelativePath newRelativePath() {
		return invoke().newRelativePath();
	}

	public static <T> StateSwitcher<T> newStateSwitcher(T default_state) {
		return invoke().newStateSwitcher(default_state);
	}

	public static List<String> split(String input_string, String splitter) {
		return invoke().split(input_string, splitter);
	}

}
