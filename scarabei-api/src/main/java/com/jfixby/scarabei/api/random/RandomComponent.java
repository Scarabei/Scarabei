
package com.jfixby.scarabei.api.random;

public interface RandomComponent {

	int newInt (int from, int to);

	double newDouble (double min, double max);

	boolean newCoin ();

	int newInt32 ();

	void setSeed (long seed);

}
