
package com.jfixby.scarabei.api.math;

public interface Matrix {

	void print (String string);

	void setValue (int i, int j, double value);

	double getValue (int i, int j);

	int getWidth ();

	int getHeight ();

	void setValue (Matrix matrix);

	void resetToIdentityMatrix ();

	boolean isSquare ();

	void resetToZeroMatrix ();

}
