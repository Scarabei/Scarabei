
package com.jfixby.scarabei.red.color;

import java.util.Random;

import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.color.ColorRandomiser;

public class RedColorRandomizer implements ColorRandomiser {

	private RedColors master;
	private long seed;
	private Random random;

	public RedColorRandomizer (RedColors redColors, long seed) {
		this.master = redColors;
		this.seed = seed;
		random = new Random(seed);

	}

	@Override
	public Color newRandomColor (float alpha) {
		final float r = random.nextFloat();
		final float g = random.nextFloat();
		final float b = random.nextFloat();
		return new RedColor(alpha, r, g, b);

	}

}
