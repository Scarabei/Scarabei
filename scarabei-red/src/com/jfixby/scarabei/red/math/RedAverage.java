
package com.jfixby.scarabei.red.math;

import java.util.LinkedList;

import com.jfixby.scarabei.api.math.Average;

public class RedAverage implements Average {
	int maxSize = Integer.MAX_VALUE;
	final LinkedList<Double> queue = new LinkedList<Double>();

	public RedAverage (final int max_num_of_values) {
		this.maxSize = max_num_of_values;
	}

	@Override
	public void addValue (final double time) {
		this.queue.add(time);
		if (this.queue.size() > this.maxSize) {
			this.queue.removeFirst();
		}
	}

	@Override
	public int size () {
		return this.queue.size();
	}

	@Override
	public double getAverage () {
		if (this.size() == 0) {
			return 0;
		}
		double result = 0;
		for (final Double value : this.queue) {
			result = result + value;
		}
		return result / this.size();
	}

	@Override
	public double getLast () {
		return this.queue.getLast();
	}

}
