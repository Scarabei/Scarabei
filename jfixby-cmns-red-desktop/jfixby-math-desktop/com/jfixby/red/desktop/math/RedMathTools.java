package com.jfixby.red.desktop.math;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.cmns.api.math.MathToolsComponent;
import com.jfixby.cmns.api.math.Matrix;
import com.jfixby.cmns.api.math.VectorTool;

public class RedMathTools implements MathToolsComponent {

	@Override
	public VectorTool newVectorTool() {
		return new RedVectorTool();
	}

	@Override
	public Matrix newScaleMatrix(double... scale) {
		return RedMatrix.newScaleMatrix(scale);
	}

	@Override
	public Matrix multiply(Matrix... matrix_list) {
		RedMatrix[] mmm = new RedMatrix[matrix_list.length];
		for (int i = 0; i < matrix_list.length; i++) {
			mmm[i] = (RedMatrix) matrix_list[i];
		}
		return RedMatrix.multiply(mmm);
	}

	@Override
	public Matrix newMatrix(int i, int j) {
		return new RedMatrix(i, j);
	}

	@Override
	public Matrix newIdentityMatrix(int n) {
		return RedMatrix.newIdentityMatrix(n);
	}

	@Override
	public Matrix inverseOf(Matrix matrix) {
		return RedMatrix.inverseOf(matrix);
	}

	@Override
	public boolean pointLiesInsideTriangle(double point_x, double point_y,
			double triangle_x0, double triangle_y0, double triangle_x1,
			double triangle_y1, double triangle_x2, double triangle_y2) {

		/* Calculate area of triangle ABC */
		double A = area(triangle_x0, triangle_y0, triangle_x1, triangle_y1,
				triangle_x2, triangle_y2);

		/* Calculate area of triangle PBC */
		double A1 = area(point_x, point_y, triangle_x2, triangle_y2,
				triangle_x1, triangle_y1);

		/* Calculate area of triangle PAC */
		double A2 = area(triangle_x1, triangle_y1, point_x, point_y,
				triangle_x0, triangle_y0);

		/* Calculate area of triangle PAB */
		double A3 = area(triangle_x0, triangle_y0, triangle_x2, triangle_y2,
				point_x, point_y);

		/* Check if sum of A1, A2 and A3 is same as A */
		return FloatMath.isWithinEpsilon(A - (A1 + A2 + A3));

		// return false;
	}

	/*
	 * A utility function to calculate area of triangle formed by (x1, y1), (x2,
	 * y2) and (x3, y3)
	 */
	double area(double x1, double y1, double x2, double y2, double x3, double y3) {
		return FloatMath
				.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2d);
	}

	@Override
	public Matrix newOffsetMatrix(double... offset) {
		return RedMatrix.newOffsetMatrix(offset);
	}

	@Override
	public Matrix newRotationMatrix(double rotationZ) {

		Matrix rotation_matrix = this.newIdentityMatrix(3);

		rotation_matrix.setValue(0, 0, +FloatMath.cos(rotationZ));
		rotation_matrix.setValue(1, 0, -FloatMath.sin(rotationZ));
		rotation_matrix.setValue(0, 1, +FloatMath.sin(rotationZ));
		rotation_matrix.setValue(1, 1, +FloatMath.cos(rotationZ));

		return rotation_matrix;
	}

	@Override
	public void multiplyAxB(Matrix a, Matrix b, Matrix result) {
		RedMatrix.multiplyAxB(a, b, result);
	}

	@Override
	public void inverse(Matrix a, Matrix inverseAresult) {
		RedMatrix.inverse(a, inverseAresult);
	}

	@Override
	public void setupOffsetMatrix(Matrix matrix, double... offset) {
		if (!matrix.isSquare()) {
			throw new Error("Matrix is not square: " + matrix);
		}
		if (matrix.getHeight() != 1 + offset.length) {

			List<Double> doubles = JUtils.newList();
			for (int i = 0; i < offset.length; i++) {
				doubles.add(offset[i]);
			}

			matrix.print("matrix");

			throw new Error(
					"Matrix dimentions are incompatible with the offset vector: "
							+ "" + doubles);
		}

		matrix.resetToIdentityMatrix();
		int n = offset.length;
		for (int i = 0; i < n; i++) {
			matrix.setValue(n, i, offset[i]);
		}
	}

	@Override
	public void setupScaleMatrix(Matrix matrix, double... scale) {
		matrix.resetToIdentityMatrix();
		int n = scale.length;
		for (int i = 0; i < n; i++) {
			matrix.setValue(i, i, scale[i]);
		}
	}

	@Override
	public void setupRotationMatrix(Matrix matrix, double rotationZ) {
		matrix.resetToIdentityMatrix();
		matrix.setValue(0, 0, +FloatMath.cos(rotationZ));
		matrix.setValue(1, 0, -FloatMath.sin(rotationZ));
		matrix.setValue(0, 1, +FloatMath.sin(rotationZ));
		matrix.setValue(1, 1, +FloatMath.cos(rotationZ));
	}

	@Override
	public void setupSkewMatrix(Matrix matrix, double... skew) {
		matrix.resetToIdentityMatrix();
		int n = skew.length;
		if (!(n == 3 || n == 2)) {
			throw new Error("Not implemented yet: " + n);
		}
		matrix.setValue(0, 1, FloatMath.sin(skew[1]));
		matrix.setValue(1, 0, FloatMath.sin(skew[0]));

	}

}
