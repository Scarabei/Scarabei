
package com.jfixby.scarabei.red.math;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.math.MathToolsComponent;
import com.jfixby.scarabei.api.math.Matrix;
import com.jfixby.scarabei.api.math.VectorTool;

public class RedMathTools implements MathToolsComponent {

	@Override
	public VectorTool newVectorTool () {
		return new RedVectorTool();
	}

	@Override
	public Matrix newScaleMatrix (final double... scale) {
		return RedMatrix.newScaleMatrix(scale);
	}

	@Override
	public Matrix multiply (final Matrix... matrix_list) {
		final RedMatrix[] mmm = new RedMatrix[matrix_list.length];
		for (int i = 0; i < matrix_list.length; i++) {
			mmm[i] = (RedMatrix)matrix_list[i];
		}
		return RedMatrix.multiply(mmm);
	}

	@Override
	public Matrix newMatrix (final int i, final int j) {
		return new RedMatrix(i, j);
	}

	@Override
	public Matrix newIdentityMatrix (final int n) {
		return RedMatrix.newIdentityMatrix(n);
	}

	@Override
	public Matrix inverseOf (final Matrix matrix) {
		return RedMatrix.inverseOf(matrix);
	}

	@Override
	public boolean pointLiesInsideTriangle (final double point_x, final double point_y, final double triangle_x0,
		final double triangle_y0, final double triangle_x1, final double triangle_y1, final double triangle_x2,
		final double triangle_y2) {

		/* Calculate area of triangle ABC */
		final double A = this.area(triangle_x0, triangle_y0, triangle_x1, triangle_y1, triangle_x2, triangle_y2);

		/* Calculate area of triangle PBC */
		final double A1 = this.area(point_x, point_y, triangle_x2, triangle_y2, triangle_x1, triangle_y1);

		/* Calculate area of triangle PAC */
		final double A2 = this.area(triangle_x1, triangle_y1, point_x, point_y, triangle_x0, triangle_y0);

		/* Calculate area of triangle PAB */
		final double A3 = this.area(triangle_x0, triangle_y0, triangle_x2, triangle_y2, point_x, point_y);

		/* Check if sum of A1, A2 and A3 is same as A */
		return FloatMath.isWithinEpsilon(A - (A1 + A2 + A3));

		// return false;
	}

	/*
	 * A utility function to calculate area of triangle formed by (x1, y1), (x2, y2) and (x3, y3)
	 */
	double area (final double x1, final double y1, final double x2, final double y2, final double x3, final double y3) {
		return FloatMath.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2d);
	}

	@Override
	public Matrix newOffsetMatrix (final double... offset) {
		return RedMatrix.newOffsetMatrix(offset);
	}

	@Override
	public Matrix newRotationMatrix (final double rotationZ) {

		final Matrix rotation_matrix = this.newIdentityMatrix(3);

		rotation_matrix.setValue(0, 0, +FloatMath.cos(rotationZ));
		rotation_matrix.setValue(1, 0, -FloatMath.sin(rotationZ));
		rotation_matrix.setValue(0, 1, +FloatMath.sin(rotationZ));
		rotation_matrix.setValue(1, 1, +FloatMath.cos(rotationZ));

		return rotation_matrix;
	}

	@Override
	public void multiplyAxB (final Matrix a, final Matrix b, final Matrix result) {
		RedMatrix.multiplyAxB(a, b, result);
	}

	@Override
	public void inverse (final Matrix a, final Matrix inverseAresult) {
		RedMatrix.inverse(a, inverseAresult);
	}

	@Override
	public void setupOffsetMatrix (final Matrix matrix, final double... offset) {
		if (!matrix.isSquare()) {
			Err.reportError("Matrix is not square: " + matrix);
		}
		if (matrix.getHeight() != 1 + offset.length) {

			final List<Double> doubles = Collections.newList();
			for (int i = 0; i < offset.length; i++) {
				doubles.add(offset[i]);
			}

			matrix.print("matrix");

			Err.reportError("Matrix dimentions are incompatible with the offset vector: " + "" + doubles);
		}

		matrix.resetToIdentityMatrix();
		final int n = offset.length;
		for (int i = 0; i < n; i++) {
			matrix.setValue(n, i, offset[i]);
		}
	}

	@Override
	public void setupScaleMatrix (final Matrix matrix, final double... scale) {
		matrix.resetToIdentityMatrix();
		final int n = scale.length;
		for (int i = 0; i < n; i++) {
			matrix.setValue(i, i, scale[i]);
		}
	}

	@Override
	public void setupRotationMatrix (final Matrix matrix, final double rotationZ) {
		matrix.resetToIdentityMatrix();
		matrix.setValue(0, 0, +FloatMath.cos(rotationZ));
		matrix.setValue(1, 0, -FloatMath.sin(rotationZ));
		matrix.setValue(0, 1, +FloatMath.sin(rotationZ));
		matrix.setValue(1, 1, +FloatMath.cos(rotationZ));
	}

	@Override
	public void setupSkewMatrix (final Matrix matrix, final double... skew) {
		matrix.resetToIdentityMatrix();
		final int n = skew.length;
		if (!(n == 3 || n == 2)) {
			Err.reportError("Not implemented yet: " + n);
		}
		matrix.setValue(0, 1, FloatMath.sin(skew[1]));
		matrix.setValue(1, 0, FloatMath.sin(skew[0]));

	}

}
