package com.jfixby.cmns.api.path;

public interface AbsolutePath<T extends MountPoint> {

	public T getMountPoint();

	public RelativePath getRelativePath();

	public AbsolutePath<T> child(String child_name);

	public AbsolutePath<T> parent();

	public AbsolutePath<T> proceed(RelativePath relative);

	public String getName();

	public boolean isRoot();

}
