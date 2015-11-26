package com.jfixby.cmns.jutils.desktop;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.lambda.λFunctionCache;
import com.jfixby.cmns.api.util.JUtils;

public class DesktopMergeSort<T extends Comparable<T>> implements λFunction<Collection<T>, Collection<T>> {
	final λFunctionCache<Collection<T>, Collection<T>> cache = Lambda.newArrayCache();
	final λFunction<Collection<T>, Collection<T>> MERGE_SORT = Lambda.cache(this, cache);

	@Override
	public Collection<T> val(Collection<T> input_list) {
		int N = input_list.size();
		if (N == 1) {
			return input_list;
		}
		if (N == 2) {
			T e0 = input_list.getElementAt(0);
			T e1 = input_list.getElementAt(1);

			if (e0.compareTo(e1) > 0) {
				List<T> result = JUtils.newList();// (2);
				result.add(input_list.getElementAt(1));
				result.add(input_list.getElementAt(0));
				return result;
			}
			return input_list;
		}
		Collection<T> sorted_tail;
		Collection<T> sorted_head;
		{
			List<T> head = JUtils.newList();// N / 2
			List<T> tail = JUtils.newList();// N - N / 2

			JUtils.arrayCopy(input_list, 0, head, N / 2);
			JUtils.arrayCopy(input_list, N / 2, tail, N - N / 2);

			// System.out.print("x");// weight indicator
			sorted_tail = MERGE_SORT.val(tail);
			sorted_head = MERGE_SORT.val(head);
		}
		int head_pointer = 0;
		int tail_pointer = 0;

		List<T> result = JUtils.newList();// N

		while (head_pointer < sorted_head.size() && tail_pointer < sorted_tail.size()) {
			T h0 = sorted_head.getElementAt(head_pointer);
			T t0 = sorted_tail.getElementAt(tail_pointer);
			if (h0.compareTo((T) t0) > 0) {
				result.add(t0);
				tail_pointer++;
			} else {
				result.add(h0);
				head_pointer++;
			}

		}

		if (head_pointer == sorted_head.size()) {
			JUtils.arrayCopy(sorted_tail, tail_pointer, result, sorted_tail.size() - tail_pointer);
		} else {
			JUtils.arrayCopy(sorted_head, head_pointer, result, sorted_head.size() - head_pointer);
		}
		return result;
	}

	public void printCache() {
		this.cache.print("cache");
	}

}
