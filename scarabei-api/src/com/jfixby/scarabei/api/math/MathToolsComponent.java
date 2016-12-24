
package com.jfixby.scarabei.api.math;

public interface MathToolsComponent {

	public VectorTool newVectorTool ();

	public Matrix newIdentityMatrix (int n);

	public Matrix newMatrix (int w, int h);

	public Matrix multiply (Matrix... matrix_list);

	public Matrix inverseOf (Matrix matrix);

	public boolean pointLiesInsideTriangle (double point_x, double point_y, double triangle_x0, double triangle_y0,
		double triangle_x1, double triangle_y1, double triangle_x2, double triangle_y2);

	Matrix newScaleMatrix (double... scale);

	public Matrix newOffsetMatrix (double... offset);

	public Matrix newRotationMatrix (double rotationZ);

	public void multiplyAxB (Matrix a, Matrix b, Matrix result);

	public void inverse (Matrix a, Matrix inverseAresult);

	public void setupOffsetMatrix (Matrix matrix, double... offset);

	public void setupScaleMatrix (Matrix matrix, double... scale);

	public void setupRotationMatrix (Matrix matrix, double radians);

	public void setupSkewMatrix (Matrix skew_matrix, double... skew);

}
