
package com.jfixby.scarabei.red.filesystem;

import java.io.IOException;
import java.util.Iterator;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.CollectionFilter;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.file.File;
import com.jfixby.scarabei.api.file.FileFilter;
import com.jfixby.scarabei.api.file.FilesList;

public class RedFilesList implements FilesList {
	final List<File> internal_list = Collections.newList();

	@Override
	public List<File> filter (final CollectionFilter<? super File> filter) {
		return this.internal_list.filter(filter);
	}

	public void add (final File absolute_file) {
		this.internal_list.add(absolute_file);
	}

	@Override
	public Iterator<File> iterator () {
		return this.internal_list.iterator();
	}

	public void addAllArrayElements (final File[] array) {
		this.internal_list.addAllArrayElements(array);
	}

	@Override
	public boolean contains (final Object element) {
		return this.internal_list.contains(element);
	}

	@Override
	public boolean containsAll (final Collection<?> list) {
		for (int i = 0; i < list.size(); i++) {
			if (!this.contains(list.getElementAt(i))) {
				return false;
			}
		}
		return true;
	}

	public boolean remove (final Object element) {
		return this.internal_list.remove(element);
	}

	@Override
	public int size () {
		return this.internal_list.size();
	}

	// @Override
	// public File getElementAt(int i) {
	// return internal_list.getElementAt(i);
	// }

	public void addAll (final Collection<? extends File> list) {
		this.internal_list.addAll(list);
	}

	@Override
	public File findChild (final String short_file_name) {
		for (int i = 0; i < this.internal_list.size(); i++) {
			final File element = this.internal_list.getElementAt(i);
			if (true) {
				final String element_short_file_name = element.getName();
				if (element_short_file_name.equals(short_file_name)) {
					return element;
				}
			}
		}
		return null;
	}

	@Override
	public String toString () {
		return "FilesList" + this.internal_list + "";
	}

	@Override
	public FilesList filter (final FileFilter filter) {
		final RedFilesList result = new RedFilesList();
		for (int i = 0; i < this.size(); i++) {
			final File child = this.getElementAt(i);
			if (filter.fits(child)) {
				result.add(child);
			}
		}
		return result;
	}

	@Override
	public java.util.List<File> toJavaList () {
		// TODO Auto-generated method stub
		return this.internal_list.toJavaList();
	}

	@Override
	public File getElementAt (final long i) {
		// TODO Auto-generated method stub
		return this.internal_list.getElementAt(i);
	}

	@Override
	public File getLast () {
		// TODO Auto-generated method stub
		return this.internal_list.getLast();
	}

	@Override
	public List<File> toList () {
		// TODO Auto-generated method stub
		return this.internal_list.toList();
	}

	@Override
	public boolean isEmpty () {
		// TODO Auto-generated method stub
		return this.internal_list.isEmpty();
	}

	@Override
	public void print (final String tag) {
		this.internal_list.print(tag);

	}

	@Override
	public void print (final String tag, final int from_index, final int to_index) {
		this.internal_list.print(tag, from_index, to_index);
	}

	@Override
	public boolean beginsWith (final Collection<File> steps) {
		return this.internal_list.beginsWith(steps);
	}

	@Override
	public FilesList filterByExtension (final String extention) {
		return this.filter(new FileFilter() {
			@Override
			public boolean fits (final File file) {
				return file.getName().toLowerCase().endsWith(extention.toLowerCase());
			}
		});
	}

	@Override
	public void deleteAll () throws IOException {
		for (int i = 0; i < this.size(); i++) {
			this.getElementAt(i).delete();
		}
	}

	@Override
	public int indexOf (final Object element) {
		return this.internal_list.indexOf(element);
	}

}
