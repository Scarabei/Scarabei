package com.jfixby.cmns.jutils.desktop;

import java.util.Iterator;
import java.util.Objects;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.CollectionScanner;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.collections.Mapping;
import com.jfixby.cmns.api.collections.Pool;
import com.jfixby.cmns.api.collections.PoolElementsSpawner;
import com.jfixby.cmns.api.collections.Queue;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.collections.StateSwitcher;
import com.jfixby.cmns.api.collections.UtilsComponent;
import com.jfixby.cmns.api.collections.ZxZ_Functuion;
import com.jfixby.cmns.api.path.AbsolutePath;
import com.jfixby.cmns.api.path.MountPoint;
import com.jfixby.cmns.api.path.RelativePath;
import com.jfixby.red.util.RedStateSwitcher;

public class DesktopUtils implements UtilsComponent {

	@Override
	public <T> List<T> newList() {
		return new DesktopList<T>();
	}

	@Override
	public <T> List<T> newList(T[] array) {
		List<T> result = new DesktopList<T>();
		result.addAllArrayElements(array);
		return result;
	}

	@Override
	public <K, V> Map<K, V> newMap() {
		return new DesktopMap<K, V>();
	}

	@Override
	public <T> Set<T> newSet() {
		Set<T> result = new DesktopSet<T>();
		return result;
	}

	@Override
	public <T> Set<T> newSet(T[] array) {
		List<T> tmp = new DesktopList<T>();
		tmp.addAllArrayElements(array);
		Set<T> result = new DesktopSet<T>();
		result.addAll(tmp);
		return result;
	}

	@Override
	public RelativePath newRelativePath(String path_string) {
		return new DesktopRelativePath(path_string);
	}

	@Override
	public <T extends MountPoint> AbsolutePath<T> newAbsolutePath(
			T mount_point, RelativePath relative) {
		return new DesktopAbsolutePath<T>(mount_point, relative);
	}

	@Override
	public RelativePath newRelativePath(List<String> steps_list) {
		return new DesktopRelativePath(steps_list);
	}

	@Override
	public <T extends MountPoint> AbsolutePath<T> newAbsolutePath(T mount_point) {
		return new DesktopAbsolutePath<T>(mount_point, new DesktopRelativePath(
				DesktopRelativePath.E));
	}

	@Override
	public RelativePath newRelativePath() {
		return new DesktopRelativePath(DesktopRelativePath.E);
	}

	@Override
	public <T> List<T> newList(
			com.jfixby.cmns.api.collections.Collection<? extends T> array) {
		List<T> list = this.newList();
		list.addAll(array);
		return list;
	}

	@Override
	public <T> ZxZ_Functuion<T> newZxZ_Function() {
		return new Desktop_ZxZ_Map_Functuion<T>();
		// return new ZxZ_Array_Functuion<T>();
	}

	@Override
	public <T> Set<T> newSet(java.util.Collection<? extends T> collection) {
		Set<T> result = new DesktopSet<T>();
		result.addJavaCollection(collection);
		// result.addAllArrayElements(array);
		return result;
	}

	@Override
	public <T> Set<T> newSet(Collection<? extends T> collection) {
		Set<T> result = new DesktopSet<T>();
		result.addAll(collection);
		// result.addAllArrayElements(array);
		return result;
	}

	@Override
	public <T> List<T> newList(java.util.Collection<? extends T> java_colletion) {
		List<T> list = this.newList();
		list.addJavaCollection(java_colletion);
		return list;
	}

	@Override
	public <K, V> Map<K, V> newMap(Mapping<? extends K, ? extends V> map) {
		DesktopMap<K, V> red_map = new DesktopMap<K, V>();
		red_map.putAll(map);
		return red_map;
	}

	@Override
	public <K, V> Map<K, V> newMap(
			java.util.Map<? extends K, ? extends V> java_map) {
		DesktopMap<K, V> red_map = new DesktopMap<K, V>();
		red_map.putJavaMap(java_map);
		return red_map;
	}

	@Override
	public <T> Queue<T> newQueue() {
		return new DesktopQueue<T>();
	}

	@Override
	public <T> Pool<T> newPool(PoolElementsSpawner<T> spawner) {
		return new DesktopPool<T>(spawner);
	}

	@Override
	public <T> void scanCollection(Collection<? extends T> collection,
			CollectionScanner<T> scanner) {
		for (int i = 0; i < collection.size(); i++) {
			T element = collection.getElementAt(i);
			scanner.scanElement(element, i, collection);
		}
	}

	@Override
	public boolean equalLists(List<?> a, List<?> b) {
		if (a.size() != b.size()) {
			return false;
		}
		Iterator<?> iA = a.iterator();
		Iterator<?> iB = b.iterator();
		while (iA.hasNext()) {
			Object ai = iA.next();
			Object bi = iB.next();
			if (ai == null) {
				if (bi != null) {
					return false;
				}
			} else {
				if (!ai.equals(bi)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public <T> StateSwitcher<T> newStateSwitcher(T default_state) {
		return new RedStateSwitcher<T>(default_state);
	}

	@Override
	public <T> T checkNull(String parameter_name, T value) {
		if (value == null) {
			throw new Error("<" + parameter_name + "> is null.");
		}
		return value;
	}

	@Override
	public <T> T checkNull(T value) {
		if (value == null) {
			throw new Error("Paremeter is null.");
		}
		return value;
	}

	@Override
	public void checkEmpty(String parameter_name, String value) {
		if ("".equals(value)) {
			throw new Error("<" + parameter_name + "> is empty.");
		}
	}

	@Override
	public List<Float> newList(float[] floats) {
		List<Float> result = this.newList();
		for (int i = 0; i < floats.length; i++) {
			Float float_val = floats[i];
			result.add(float_val);
		}
		return result;
	}

	@Override
	public List<String> split(String input_string, String splitter) {
		JUtils.checkNull("input_string", input_string);
		JUtils.checkNull("splitter", splitter);
		return JUtils.newList(input_string.split(splitter));
	}

	@Override
	public boolean beginsWith(Collection<?> list, Collection<?> with) {
		JUtils.checkNull("list", list);
		JUtils.checkNull("with", with);
		if (list.size() < with.size()) {
			return false;
		}
		if (list.equals(with)) {
			return true;
		}
		// L.d("compare");
		// list.print("a");
		// with.print("b");

		for (int i = 0; i < with.size(); i++) {
			Object a = with.getElementAt(i);
			Object b = list.getElementAt(i);
			if (!Objects.equals(a, b)) {
				// L.d("false", a + " != " + b);
				return false;
			}
		}
		return true;

	}

	@SuppressWarnings("unchecked")
	@Override
	public <Q, P, Cp extends EditableCollection<P>> Cp castCollection(
			Collection<Q> input, Cp output) {
		for (Q i : input) {
			P p = (P) i;
			output.add(p);
		}
		return output;
	}

	@Override
	public <Q, P> List<P> castCollection(Collection<Q> input) {
		return this.castCollection(input, JUtils.newList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <A, B, X, Y, Mp extends Map<A, B>> Mp castMap(Mapping<X, Y> input,
			Mp output) {
		for (X iK : input.keys()) {
			A oK = (A) iK;
			B oV = (B) input.get(iK);
			output.put(oK, oV);
		}
		return output;

	}

	@Override
	public <A, B, X, Y> Map<A, B> castMap(Mapping<X, Y> input) {
		return this.castMap(input, JUtils.newMap());
	}

}
