
package com.jfixby.scarabei.api.util.path;

public interface AbsolutePath<T extends MountPoint> {

	public T getMountPoint ();

	public RelativePath getRelativePath ();

	public AbsolutePath<T> child (String child_name);

	public AbsolutePath<T> parent ();

	public AbsolutePath<T> proceed (RelativePath relative);

	public String getName ();

	public boolean isRoot ();

	public boolean beginsWith (AbsolutePath<? extends T> rootPath);

}
