
package com.jfixby.scarabei.red.util;

import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.util.JUtils;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.MountPoint;
import com.jfixby.scarabei.api.util.path.RelativePath;

class RedAbsolutePath<T extends MountPoint> implements AbsolutePath<T> {

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.mount_point == null) ? 0 : this.mount_point.hashCode());
		result = prime * result + ((this.relative == null) ? 0 : this.relative.hashCode());
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RedAbsolutePath<?> other = (RedAbsolutePath<?>)obj;
		if (this.mount_point == null) {
			if (other.mount_point != null) {
				return false;
			}
		} else if (!this.mount_point.equals(other.mount_point)) {
			return false;
		}
		if (this.relative == null) {
			if (other.relative != null) {
				return false;
			}
		} else if (!this.relative.equals(other.relative)) {
			return false;
		}
		return true;
	}

	private final T mount_point;
	private final RedRelativePath relative;

	public RedAbsolutePath (final T mount_point, final RelativePath relative) {
		this.mount_point = mount_point;
		this.relative = (RedRelativePath)relative;
		if (mount_point == null) {
			Err.reportError("MountPoint == null");
		}
		if (relative == null) {
			Err.reportError("RelativePath == null");
		}
	}

	@Override
	public T getMountPoint () {
		return this.mount_point;
	}

	@Override
	public RelativePath getRelativePath () {
		return this.relative;
	}

	@Override
	public String getName () {
		final List<String> steps = this.relative.stepsList();
		if (steps.size() == 0) {
			Err.reportError("This is root!");
		}
		return steps.getLast();
	}

	@Override
	public AbsolutePath<T> child (final String child_name) {
		final RelativePath child_relative = this.relative.child(child_name);

		final AbsolutePath<T> result = JUtils.newAbsolutePath(this.mount_point, child_relative);
		return result;
	}

	@Override
	public String toString () {
		return "@[" + this.mount_point.toString() + "] " + this.relative.getPathString();
	}

	@Override
	public AbsolutePath<T> parent () {
		final RelativePath parent = this.relative.parent();
		final AbsolutePath<T> result = JUtils.newAbsolutePath(this.mount_point, parent);
		return result;
	}

	@Override
	public AbsolutePath<T> proceed (final RelativePath relative) {
		final RelativePath incremented = this.relative.proceed(relative);
		return JUtils.newAbsolutePath(this.mount_point, incremented);
	}

	@Override
	public boolean isRoot () {
		return this.relative.isRoot();
	}

	@Override
	public boolean beginsWith (final AbsolutePath<? extends T> other) {
		if (!this.mount_point.equals(other.getMountPoint())) {
			return false;
		}
		return this.relative.beginsWith(other.getRelativePath());
	}
}
