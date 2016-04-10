
package com.jfixby.red.color;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.jfixby.cmns.api.collections.CollectionFilter;
import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.ColorDistance;
import com.jfixby.cmns.api.color.ColorSet;
import com.jfixby.cmns.api.color.Colors;

public class RedColorsSet implements ColorSet {

	private ColorDistance distance;

	public RedColorsSet (ColorDistance distance) {
		this.distance = distance;
	}

	final Set<Color> colors = Collections.newSet();

	@Override
	public int size () {
		return colors.size();
	}

	@Override
	public Color getElementAt (long i) {
		return colors.getElementAt(i);
	}

	@Override
	public Color findClosest (Color original) {
		if (this.colors.size() == 0) {
			throw new Error("ColorsSet is empty.");
		}
		Color closest = colors.getElementAt(0);// assuming size >0;
		float current_distance = Colors.distanceRGB().measure(original, closest);

		for (int i = 1; i < this.colors.size(); i++) {
			if (current_distance == 0) {
				return closest;
			}
			final Color candidate_i = this.colors.getElementAt(i);
			float new_distance = distance.measure(candidate_i, original);
			if (new_distance < current_distance) {
				current_distance = new_distance;
				closest = candidate_i;
			}
		}
		return closest;

	}

	@Override
	public void print (String tag) {
		this.colors.print(tag);
	}

	@Override
	public void sort () {
		colors.sort();
	}

	@Override
	public boolean remove (Object element) {
		return colors.remove(element);
	}

	@Override
	public void clear () {
		colors.clear();
	}

	@Override
	public boolean add (Color element) {
		return colors.add(element);
	}

	@Override
	public int indexOf (Object element) {
		return colors.indexOf(element);
	}

	@Override
	public void reverse () {
		colors.reverse();
	}

	@Override
	public Color removeElementAt (long i) {
		return colors.removeElementAt(i);
	}

	@Override
	public Color removeLast () {
		return colors.removeLast();
	}

	@Override
	public void addJavaCollection (Collection<? extends Color> java_collection) {
		colors.addJavaCollection(java_collection);
	}

	@Override
	public void addAllArrayElements (Color[] array) {
		colors.addAllArrayElements(array);
	}

	@Override
	public void addAll (com.jfixby.cmns.api.collections.Collection<? extends Color> list) {
		colors.addAll(list);
	}

	@Override
	public void addAll (Iterable<? extends Color> list) {
		colors.addAll(list);
	}

	@Override
	public void removeAll (com.jfixby.cmns.api.collections.Collection<? extends Object> list) {
		colors.removeAll(list);
	}

	@Override
	public void sort (Comparator<? super Color> comparator) {
		colors.sort(comparator);
	}

	@Override
	public EditableCollection<Color> splitAt (int index) {
		return colors.splitAt(index);
	}

	@Override
	public boolean contains (Object element) {
		return colors.contains(element);
	}

	@Override
	public List<Color> toJavaList () {
		return colors.toJavaList();
	}

	@Override
	public Iterator<Color> iterator () {
		return colors.iterator();
	}

	@Override
	public Color getLast () {
		return colors.getLast();
	}

	@Override
	public com.jfixby.cmns.api.collections.List<Color> toList () {
		return colors.toList();
	}

	@Override
	public boolean isEmpty () {
		return colors.isEmpty();
	}

	@Override
	public void print (String tag, int from_index, int to_index) {
		colors.print(tag, from_index, to_index);
	}

	@Override
	public boolean beginsWith (com.jfixby.cmns.api.collections.Collection<Color> steps) {
		return colors.beginsWith(steps);
	}

	@Override
	public com.jfixby.cmns.api.collections.List<Color> filter (CollectionFilter<? super Color> filter) {
		return colors.filter(filter);
	}

}
