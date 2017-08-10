
package com.jfixby.scarabei.api.util;

import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Set;
import com.jfixby.scarabei.api.java.ByteArray;
import com.jfixby.scarabei.api.taskman.SimpleProgress;
import com.jfixby.scarabei.api.util.path.AbsolutePath;
import com.jfixby.scarabei.api.util.path.MountPoint;
import com.jfixby.scarabei.api.util.path.RelativePath;

public interface UtilsComponent {

	RelativePath newRelativePath (String path_string);

	RelativePath newRelativePath (Collection<String> steps_list);

	RelativePath newRelativePath (java.util.List<String> steps_list);

	RelativePath newRelativePath ();

	<T extends MountPoint> AbsolutePath<T> newAbsolutePath (T mount_point, RelativePath relative);

	<T extends MountPoint> AbsolutePath<T> newAbsolutePath (T mount_point);

	<T> StateSwitcher<T> newStateSwitcher (T default_state);

	<T> Set<T> intersectCollections (Collection<T> listA, Collection<T> listB);

	boolean equalObjects (final Object a, final Object b);

	BinaryCode binaryCodeOf (int bits, int numberOfBits);

	EditableBinaryCode newBinaryCode ();

	ByteArray newByteArray (int size);

	ByteArray newByteArray (byte[] bytes);

	ProgressIndicator newProgressIndicator ();

	ID nameOf (Class<?> type);

	SimpleProgress newSimpleProgress ();

}
