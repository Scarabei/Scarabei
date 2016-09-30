
package com.jfixby.red.collections;

import java.util.Comparator;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Heap;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.log.L;
import com.jfixby.cmns.api.util.JUtils;

public class RedHeap<T> implements Heap<T> {

	final List<T> array = Collections.newList();
	private final Comparator<? super T> comparator;

	public RedHeap (final Comparator<? super T> comparator) {
		this.comparator = comparator;
// list.sort(c);
	}

	@Override
	public void clear () {
		this.array.clear();
	}

	@Override
	public int size () {
		return this.array.size();
	}

	@Override
	public T remove () {
		swap(this.array, 0, this.size() - 1);
		final T element = this.array.removeElementAt(this.size() - 1);
		this.heapDown(0);
// this.print("heap -" + element);
		return element;
	}

	@Override
	public void addAll (final T... elements) {
		for (int i = 0; i < elements.length; i++) {
			this.add(elements[i]);
		}
	}

	@Override
	public void add (final T element) {
		this.array.add(element);
		this.heapUp(this.size() - 1);
// this.print("heap +" + element);
	}

	private void heapUp (final int childIndex) {
		if (childIndex == 0) {
			return;
		}
		final int parentIndex = indexOfParent(childIndex);

		final T parent = this.array.getElementAt(parentIndex);
		final T child = this.array.getElementAt(childIndex);

		if (this.comparator.compare(child, parent) > 0) {
			swap(this.array, parentIndex, childIndex);
			this.heapUp(parentIndex);
		}

	}

	private void heapDown (final int nodeIndex) {
		if (nodeIndex >= this.size()) {
			return;
		}
		final T parent = this.array.getElementAt(nodeIndex);

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
			final T leftChild = this.array.getElementAt(leftIndex);
			final T rightChild = this.array.getElementAt(rightIndex);

			if (this.comparator.compare(leftChild, rightChild) > 0) {
				childIndex = leftIndex;

				final T child = this.array.getElementAt(childIndex);
				if (this.comparator.compare(child, parent) > 0) {
					swap(this.array, parentIndex, childIndex);
					this.heapDown(childIndex);
					return;
				}

			} // else
			{
				childIndex = rightIndex;
				final T child = this.array.getElementAt(childIndex);
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

		final T child = this.array.getElementAt(childIndex);
		if (this.comparator.compare(child, parent) > 0) {
			swap(this.array, parentIndex, childIndex);
			this.heapDown(childIndex);
		}

	}

// @Override
// public void remove (final T element) {
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
	public void print (final String tag) {
		L.d("---Heap[" + tag + "]----------------");
		this.printNode("", 0, NODE_PRINT_TYPE.ROOT);
	}

	private void printNode (final String globalPrefix, final int index, final NODE_PRINT_TYPE type) {
		if (index >= this.size()) {
			return;
		}
		final T e = this.array.getElementAt(index);

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
			this.printNode(nextPrefix, indexOfLeftChild(index), NODE_PRINT_TYPE.MIDDLE);
			this.printNode(nextPrefix, indexOfRightChild(index), NODE_PRINT_TYPE.LAST);
		}

	}

	private int indexOf (final T element) {
		int index = 0;
		while (true) {
			if (index >= this.size()) {
				return -1;
			}
			final T current = this.array.getElementAt(index);
			final int compare = this.comparator.compare(current, element);
			if (compare == 0) {
				return index;
			}
			if (compare > 0) {
				index = indexOfRightChild(index);
				continue;
			}
			if (compare < 0) {
				index = indexOfLeftChild(index);
				continue;
			}
		}
	}

	@Override
	public T peek () {
		if (this.size() == 0) {
			return null;
		}
		return this.array.getElementAt(0);
	}

	public static <T> void swap (final List<T> A, final int x, final int y) {
		final T tmp = A.getElementAt(x);
		A.setElementAt(A.getElementAt(y), x);
		A.setElementAt(tmp, y);
	}

	public static int indexOfParent (final int nodID) {
		return (nodID - 1) / 2;
	}

	public static int indexOfLeftChild (final int nodID) {
		return nodID * 2 + 1;
	}

	public static int indexOfRightChild (final int nodID) {
		return nodID * 2 + 2;
	}

}
