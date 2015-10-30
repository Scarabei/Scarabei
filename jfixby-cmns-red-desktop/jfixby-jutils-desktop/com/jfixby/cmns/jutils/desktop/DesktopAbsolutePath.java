package com.jfixby.cmns.jutils.desktop;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.cmns.api.path.MountPoint;
import com.jfixby.cmns.api.path.RelativePath;

public class DesktopAbsolutePath<T extends MountPoint> implements
		AbsolutePath<T> {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mount_point == null) ? 0 : mount_point.hashCode());
		result = prime * result
				+ ((relative == null) ? 0 : relative.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DesktopAbsolutePath<?> other = (DesktopAbsolutePath<?>) obj;
		if (mount_point == null) {
			if (other.mount_point != null)
				return false;
		} else if (!mount_point.equals(other.mount_point))
			return false;
		if (relative == null) {
			if (other.relative != null)
				return false;
		} else if (!relative.equals(other.relative))
			return false;
		return true;
	}

	private T mount_point;
	private DesktopRelativePath relative;

	public DesktopAbsolutePath(T mount_point, RelativePath relative) {
		this.mount_point = mount_point;
		this.relative = (DesktopRelativePath) relative;
		if (mount_point == null) {
			throw new Error("MountPoint == null");
		}
		if (relative == null) {
			throw new Error("RelativePath == null");
		}
	}

	@Override
	public T getMountPoint() {
		return mount_point;
	}

	@Override
	public RelativePath getRelativePath() {
		return this.relative;
	}

	@Override
	public String getName() {
		List<String> steps = this.relative.stepsList();
		if (steps.size() == 0) {
			throw new Error("This is root!");
		}
		return steps.getLast();
	}

	@Override
	public AbsolutePath<T> child(String child_name) {
		RelativePath child_relative = JUtils.newRelativePath(relative
				.getPathString() + RelativePath.SEPARATOR + child_name);
		AbsolutePath<T> result = JUtils.newAbsolutePath(mount_point,
				child_relative);
		return result;
	}

	@Override
	public String toString() {
		return "@[" + this.mount_point.toString() + "] "
				+ this.relative.getPathString();
	}

	@Override
	public AbsolutePath<T> parent() {
		RelativePath parent = this.relative.parent();
		AbsolutePath<T> result = JUtils.newAbsolutePath(mount_point, parent);
		return result;
	}

	@Override
	public AbsolutePath<T> proceed(RelativePath relative) {
		RelativePath incremented = this.relative.proceed(relative);
		return JUtils.newAbsolutePath(mount_point, incremented);
	}

	@Override
	public boolean isRoot() {
		return this.relative.isRoot();
	}
}
