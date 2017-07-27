
package com.jfixby.scarabei.red.random;

import com.jfixby.scarabei.api.random.RandomComponent;

public class RedRandom implements RandomComponent {

	final java.util.Random random = new java.util.Random();
	private int tmpInt;
	private double tmpDouble;

	@Override
	public int newInt (final int min, final int max) {
		for (int i = 0; i < 3 + this.random.nextInt(4); i++) {
			this.tmpInt = this.random.nextInt((max - min) + 1) + min;
		}
		return this.tmpInt;
	}

	@Override
	public double newDouble (final double min, final double max) {
		for (int i = 0; i < 3 + this.random.nextInt(4); i++) {
			this.tmpDouble = this.random.nextDouble() * (max - min) + min;
		}
		return this.tmpDouble;
	}

	@Override
	public boolean newCoin () {
		return this.newDouble(0, 1) >= 0.5;
	}

	@Override
	public int newInt32 () {
		return this.random.nextInt();
	}

	@Override
	public void setSeed (final long seed) {
		this.random.setSeed(seed);
	}

}
