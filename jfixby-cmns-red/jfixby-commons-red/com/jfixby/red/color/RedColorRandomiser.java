package com.jfixby.red.color;

import java.util.Random;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.ColorRandomiser;

public class RedColorRandomiser implements ColorRandomiser {

	private RedColors master;
	private long seed;
	private Random random;

	public RedColorRandomiser(RedColors redColors, long seed) {
		this.master = redColors;
		this.seed = seed;
		random = new Random(seed);

	}

	@Override
	public Color newRandomColor(float alpha) {
		final float r = random.nextFloat();
		final float g = random.nextFloat();
		final float b = random.nextFloat();
		return new RedColor(alpha, r, g, b);

	}

}
