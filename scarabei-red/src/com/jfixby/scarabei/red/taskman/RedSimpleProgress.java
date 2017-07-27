
package com.jfixby.scarabei.red.taskman;

import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.math.IntegerMath;
import com.jfixby.scarabei.api.taskman.SimpleProgress;
import com.jfixby.scarabei.api.taskman.TaskProgress;

public class RedSimpleProgress implements TaskProgress, SimpleProgress {
	boolean is_done = false;
	private int total;
	private float processed;

	@Override
	public boolean isDone () {
		return this.is_done;
	}

	@Override
	public int getTotalSteps () {
		return this.total;
	}

	@Override
	public float getProcessedSteps () {
		return this.processed;
	}

	@Override
	public float getProgressValue () {
		return this.processed * 1f / this.total;
	}

	static int max_chars = 80;

	@Override
	public String toString () {
		final float float_value = this.getProgressValue();

		final int percent = (int)IntegerMath.limit(0, (int)(float_value * 100), 100);
		if (this.is_done) {
			return "" + this.chars(this.getProgressValue(), max_chars) + " " + 100 + "% DONE!";
		}
		return "" + this.chars(this.getProgressValue(), max_chars) + " " + (percent) + "%";
	}

	private String chars (final float progressValue, final int max_chars) {
		long N = FloatMath.round(max_chars * progressValue);
		final StringBuilder b = new StringBuilder();
		N = IntegerMath.limit(0, N, max_chars);
		for (int i = 0; i < N; i++) {
			b.append(".");
		}
		return b.toString();
	}

	@Override
	public void setTotal (final int l) {
		this.total = l;
	}

	@Override
	public void updateProcessed (final float processed) {
		this.processed = processed;

	}

	@Override
	public void setIsDone () {
		this.is_done = true;
	}
}
