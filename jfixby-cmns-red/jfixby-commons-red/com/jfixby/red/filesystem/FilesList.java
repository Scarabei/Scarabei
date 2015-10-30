package com.jfixby.red.filesystem;

import java.util.Iterator;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.filesystem.File;
import com.jfixby.cmns.api.math.IntegerMath;
import com.jfixby.cmns.api.path.ChildrenList;
import com.jfixby.cmns.api.path.FileFilter;

public class FilesList implements ChildrenList {
	final List<File> internal_list = JUtils.newList();

	public void add(File absolute_file) {
		internal_list.add(absolute_file);
	}

	@Override
	public Iterator<File> iterator() {
		return internal_list.iterator();
	}

	public void addAllArrayElements(File[] array) {
		internal_list.addAllArrayElements(array);
	}

	public boolean contains(Object element) {
		return internal_list.contains(element);
	}

	public boolean remove(Object element) {
		return internal_list.remove(element);
	}

	@Override
	public int size() {
		return internal_list.size();
	}

	// @Override
	// public File getElementAt(int i) {
	// return internal_list.getElementAt(i);
	// }

	public void addAll(List<? extends File> list) {
		this.internal_list.addAll(list);
	}

	@Override
	public File findChild(String short_file_name) {
		for (int i = 0; i < this.internal_list.size(); i++) {
			File element = this.internal_list.getElementAt(i);
			if (true) {
				String element_short_file_name = element.getName();
				if (element_short_file_name.equals(short_file_name)) {
					return element;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "FilesList" + internal_list + "";
	}

	@Override
	public void print() {
		internal_list.print("FilesList");
	}

	@Override
	public ChildrenList filter(FileFilter filter) {
		final FilesList result = new FilesList();
		for (int i = 0; i < this.size(); i++) {
			final File child = this.getElementAt(i);
			if (filter.fits(child)) {
				result.add(child);
			}
		}
		return result;
	}

	@Override
	public java.util.List<File> toJavaList() {
		// TODO Auto-generated method stub
		return internal_list.toJavaList();
	}

	@Override
	public File getElementAt(long i) {
		// TODO Auto-generated method stub
		return internal_list.getElementAt(i);
	}

	@Override
	public File getLast() {
		// TODO Auto-generated method stub
		return internal_list.getLast();
	}

	@Override
	public List<File> toList() {
		// TODO Auto-generated method stub
		return internal_list.toList();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return internal_list.isEmpty();
	}

	@Override
	public void print(String tag) {
		internal_list.print(tag);

	}

	@Override
	public void print(String tag, int from_index, int to_index) {
		List<File> l = JUtils.newList();
		int N = this.size();
		int a = 0;
		int b = N;
		a = (int) IntegerMath.limit(0, from_index, N);
		b = (int) IntegerMath.limit(0, to_index, N);

		int d = 1;
		if (a > b) {
			d = -1;
		}
		for (int i = a; i < b; i = i + d) {
			File element = this.getElementAt(i);
			l.add(element);
		}
		l.print(tag);
	}

	@Override
	public boolean beginsWith(Collection<File> steps) {
		return this.internal_list.beginsWith(steps);
	}

}
