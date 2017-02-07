
package com.jfixby.scarabei.api.geometry;

public interface LinearCombinable<I, O> {

	O getLinearSum (double alpha, I other, double betta, O output);

}
