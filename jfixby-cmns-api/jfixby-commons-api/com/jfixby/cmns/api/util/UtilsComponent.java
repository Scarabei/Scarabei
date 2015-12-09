package com.jfixby.cmns.api.util;

import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.cmns.api.path.MountPoint;
import com.jfixby.cmns.api.path.RelativePath;

public interface UtilsComponent {

	RelativePath newRelativePath(String path_string);

	RelativePath newRelativePath(List<String> steps_list);

	RelativePath newRelativePath();

	<T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point, RelativePath relative);

	<T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point);

	<T> StateSwitcher<T> newStateSwitcher(T default_state);

	List<String> split(String input_string, String splitter);

}
