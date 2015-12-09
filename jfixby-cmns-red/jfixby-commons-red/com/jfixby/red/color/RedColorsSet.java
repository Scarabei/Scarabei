package com.jfixby.red.color;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.color.ColorsSet;

public class RedColorsSet implements ColorsSet {

	final Set<Color> colors = JUtils.newSet();

	@Override
	public void add(Color color) {
		colors.add(color);
	}

	@Override
	public int size() {
		return colors.size();
	}

	@Override
	public Color get(int i) {
		return colors.getElementAt(i);
	}

	@Override
	public Color findClosestTo(Color original) {
		if (this.colors.size() == 0) {
			throw new Error("ColorsSet is empty.");
		}
		Color closest = colors.getElementAt(0);// assuming size >0;
		double current_distance = Colors.distance(original,
				closest);
	
		for (int i = 1; i < this.colors.size(); i++) {
			Color candidate_i = this.colors.getElementAt(i);
			double new_distance = Colors.distance(candidate_i,
					original);
			if (new_distance < current_distance) {
				current_distance = new_distance;
				closest = candidate_i;
			
			}
		}
		return closest;

	}

}
