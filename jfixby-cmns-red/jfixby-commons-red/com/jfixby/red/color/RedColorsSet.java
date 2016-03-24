package com.jfixby.red.color;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Set;
import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.ColorDistance;
import com.jfixby.cmns.api.color.ColorSet;
import com.jfixby.cmns.api.color.Colors;

public class RedColorsSet implements ColorSet {

    private ColorDistance distance;

    public RedColorsSet(ColorDistance distance) {
	this.distance = distance;
    }

    final Set<Color> colors = Collections.newSet();

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
    public Color findClosest(Color original) {
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
    public void print(String tag) {
	this.colors.print(tag);
    }

}
