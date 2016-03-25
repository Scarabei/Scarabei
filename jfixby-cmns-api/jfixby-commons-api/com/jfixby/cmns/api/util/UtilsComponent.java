package com.jfixby.cmns.api.util;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.util.path.AbsolutePath;
import com.jfixby.cmns.api.util.path.MountPoint;
import com.jfixby.cmns.api.util.path.RelativePath;

public interface UtilsComponent {

    RelativePath newRelativePath(String path_string);

    RelativePath newRelativePath(List<String> steps_list);

    RelativePath newRelativePath(java.util.List<String> steps_list);

    RelativePath newRelativePath();

    <T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point, RelativePath relative);

    <T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point);

    <T> StateSwitcher<T> newStateSwitcher(T default_state);

    List<String> split(String input_string, String splitter);

    String newString(byte[] data);

    <T> Set<T> intersectCollections(Collection<T> listA, Collection<T> listB);

    String truncated(String data, int begin_char, int end_char);

    boolean equalObjects(final Object a, final Object b);

    BitForm bitformOf(int bits, int numberOfBits);

    BitForm newBitForm();
}
