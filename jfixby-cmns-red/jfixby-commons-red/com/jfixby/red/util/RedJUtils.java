package com.jfixby.red.util;

import java.io.UnsupportedEncodingException;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.err.Err;
import com.jfixby.cmns.api.util.StateSwitcher;
import com.jfixby.cmns.api.util.UtilsComponent;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.MountPoint;
import com.jfixby.cmns.api.util.path.RelativePath;

public class RedJUtils implements UtilsComponent {
	@Override
	public List<String> split(String input_string, String splitter) {
		Debug.checkNull("input_string", input_string);
		Debug.checkNull("splitter", splitter);
		return Collections.newList(input_string.split(splitter));
	}

	@Override
	public <T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point) {
		return new RedAbsolutePath<T>(mount_point, new RedRelativePath(RedRelativePath.E));
	}

	@Override
	public RelativePath newRelativePath() {
		return new RedRelativePath(RedRelativePath.E);
	}

	@Override
	public RelativePath newRelativePath(String path_string) {
		return new RedRelativePath(path_string);
	}

	@Override
	public <T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point, RelativePath relative) {
		return new RedAbsolutePath<T>(mount_point, relative);
	}

	@Override
	public RelativePath newRelativePath(List<String> steps_list) {
		return new RedRelativePath(steps_list);
	}

	@Override
	public <T> StateSwitcher<T> newStateSwitcher(T default_state) {
		return new RedStateSwitcher<T>(default_state);
	}

	@Override
	public RelativePath newRelativePath(java.util.List<String> steps_list) {
		return this.newRelativePath(Collections.newList(steps_list));
	}

	@Override
	public String newString(byte[] data) {
		try {
			return new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Err.reportError(e);
		}
		throw new Error();
	}

	@Override
	public <T> Set<T> intersectCollections(Collection<T> listA, Collection<T> listB) {
		Set<T> intersection = Collections.newSet();
		for (int i = 0; i < listA.size(); i++) {
			T a = listA.getElementAt(i);
			if (listB.contains(a)) {
				intersection.add(a);
			}
		}
		for (int i = 0; i < listB.size(); i++) {
			T b = listB.getElementAt(i);
			if (listA.contains(b)) {
				intersection.add(b);
			}
		}
		return intersection;
	}
}
