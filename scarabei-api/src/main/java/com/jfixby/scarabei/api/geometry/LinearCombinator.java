
package com.jfixby.scarabei.api.geometry;

public interface LinearCombinator<T> {

	public void setA (T a);

	public void setB (T b);

	public T getLinearCombination (double alpha, double betta);

}
