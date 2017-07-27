
package com.jfixby.scarabei.red.collections;

import java.util.ArrayList;
import java.util.Comparator;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Heap;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.util.JUtils;

final public class RedHeap<T> implements Heap<T> {

	final ArrayList<T> array = new ArrayList<T>();
	private final Comparator<? super T> comparator;

	public RedHeap (final Comparator<? super T> comparator) {
		this.comparator = comparator;
// list.sort(c);
	}

	@Override
	final public void clear () {
		this.array.clear();
	}

	@Override
	final public int size () {
		return this.array.size();
	}

	@Override
	final public T remove () {
		swap(this.array, 0, this.size() - 1);
		final T element = this.array.remove(this.size() - 1);
		this.heapDown(0);
		return element;
	}

	@Override
	final public void addAll (final T... elements) {
		for (int i = 0; i < elements.length; i++) {
			this.add(elements[i]);
		}
	}

	@Override
	final public void add (final T element) {
		this.array.add(element);
		this.heapUp(this.size() - 1);
// this.print("heap +" + element);
	}

	private void heapUp (final int childIndex) {
		if (childIndex == 0) {
			return;
		}
		final int parentIndex = indexOfParent(childIndex);

		final T parent = this.array.get(parentIndex);
		final T child = this.array.get(childIndex);

		if (this.comparator.compare(child, parent) >= 0) {
			swap(this.array, parentIndex, childIndex);
			this.heapUp(parentIndex);
		}

	}

	private void heapDown (final int nodeIndex) {
		if (nodeIndex >= this.size()) {
			return;
		}
		final T parent = this.array.get(nodeIndex);

		final int leftIndex = indexOfLeftChild(nodeIndex);
		final int rightIndex = indexOfRightChild(nodeIndex);

		final boolean hasLeftChild = leftIndex < this.size();
		final boolean hasRightChild = rightIndex < this.size();

		if (!hasLeftChild && !hasRightChild) {
			return;
		}

		final int parentIndex = nodeIndex;

		int childIndex;

		if (hasLeftChild && hasRightChild) {
			final T leftChild = this.array.get(leftIndex);
			final T rightChild = this.array.get(rightIndex);

			if (this.comparator.compare(leftChild, rightChild) > 0) {
				childIndex = leftIndex;

				final T child = this.array.get(childIndex);
				if (this.comparator.compare(child, parent) > 0) {
					swap(this.array, parentIndex, childIndex);
					this.heapDown(childIndex);
					return;
				}

			} // else
			{
				childIndex = rightIndex;
				final T child = this.array.get(childIndex);
				if (this.comparator.compare(child, parent) > 0) {
					swap(this.array, parentIndex, childIndex);
					this.heapDown(childIndex);
					return;
				}
			}

			return;
		}

		if (hasLeftChild) {
			childIndex = leftIndex;
		} else {// if (hasRightChild) {
			childIndex = rightIndex;
		}

		final T child = this.array.get(childIndex);
		if (this.comparator.compare(child, parent) > 0) {
			swap(this.array, parentIndex, childIndex);
			this.heapDown(childIndex);
		}

	}

// @Override
// final public void remove (final T element) {
// Debug.component().checkNull("argument is null", element);
// final int index = this.indexOf(element);
// if (index < 0) {
// return;
// }
// swap(this.array, index, this.size() - 1);
// this.array.removeElementAt(this.size() - 1);
// this.heapUp(index);
// }

	enum NODE_PRINT_TYPE {
		ROOT, MIDDLE, LAST
	}

	@Override
	final public void print (final String tag) {
		L.d("---Heap(" + this.size() + ")[" + tag + "]----------------");
		this.printNode("", 0, NODE_PRINT_TYPE.ROOT);
	}

	private void printNode (final String globalPrefix, final int index, final NODE_PRINT_TYPE type) {
		if (index >= this.size()) {
			return;
		}
		final T e = this.array.get(index);

		final boolean hasLeftChild = indexOfLeftChild(index) < this.size();
		final boolean hasRightChild = indexOfRightChild(index) < this.size();
		String nextPrefix;

		final String nodeHeader = "[" + e + "]";
		final int offset = nodeHeader.length() / 2;

		if (type == NODE_PRINT_TYPE.ROOT) {
			nextPrefix = globalPrefix + JUtils.prefix(" ", offset);
			L.d(globalPrefix + nodeHeader);
		} else if (type == NODE_PRINT_TYPE.LAST) {
			nextPrefix = globalPrefix + JUtils.prefix(" ", offset);
			L.d(globalPrefix + "└" + nodeHeader);
		} else { // (type == NODE_PRINT_TYPE.MIDDLE) {
			nextPrefix = globalPrefix + "│" + JUtils.prefix(" ", offset);
			L.d(globalPrefix + "├" + nodeHeader);
		}

		if (hasLeftChild & !hasRightChild) {
			this.printNode(nextPrefix, indexOfLeftChild(index), NODE_PRINT_TYPE.LAST);
		}
		if (!hasLeftChild & hasRightChild) {
			this.printNode(nextPrefix, indexOfRightChild(index), NODE_PRINT_TYPE.LAST);
		}
		if (hasLeftChild & hasRightChild) {
			this.printNode(nextPrefix, indexOfRightChild(index), NODE_PRINT_TYPE.MIDDLE);
			this.printNode(nextPrefix, indexOfLeftChild(index), NODE_PRINT_TYPE.LAST);
		}

	}

	@Override
	final public T peek () {
		if (this.size() == 0) {
			return null;
		}
		return this.array.get(0);
	}

	final public static <T> void swap (final ArrayList<T> A, final int x, final int y) {
		final T tmp = A.get(x);
		A.set(x, A.get(y));
		A.set(y, tmp);
	}

	final public static int indexOfParent (final int nodID) {
		return (nodID - 1) / 2;
	}

	final public static int indexOfLeftChild (final int nodID) {
		return nodID * 2 + 1;
	}

	final public static int indexOfRightChild (final int nodID) {
		return nodID * 2 + 2;
	}

	@Override
	public void addAll (final Collection<? extends T> other) {
		for (int i = 0; i < other.size(); i++) {
			this.add(other.getElementAt(i));
		}
	}

}
