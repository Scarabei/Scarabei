
package com.jfixby.scarabei.red.math;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.math.Matrix;

public class RedMatrix implements Matrix {

	final private int w;
	final private int h;
	final double[][] matrix;

	public RedMatrix (final int w, final int h) {
		this.w = w;
		this.h = h;
		this.matrix = new double[w][h];
	}

	@Override
	public void setValue (final int x, final int y, final double Axy) {
		this.matrix[x][y] = Axy;
	}

	@Override
	public double getValue (final int x, final int y) {
		return this.matrix[x][y];
	}

	public static final RedMatrix newIdentityMatrix (final int n) {
		final RedMatrix E = new RedMatrix(n, n);
		for (int i = 0; i < n; i++) {
			E.setValue(i, i, 1d);
		}
		return E;
	}

	public static RedMatrix newOffsetMatrix (final double... offset) {
		final int n = offset.length;
		final RedMatrix E = RedMatrix.newIdentityMatrix(n + 1);
		for (int i = 0; i < n; i++) {
			E.setValue(n, i, offset[i]);
		}
		return E;
	}

	// public static RedMatrix newRotationMatrix(double... angle) {
	// RedMatrix E = RedMatrix.newIdentityMatrix(angle.length);
	// for (int i = 0; i < angle.length; i++) {
	// E.setValue(0, 0, FloatMath.cos(angle));
	// E.setValue(1, 0, -FloatMath.sin(angle));
	// E.setValue(0, 1, FloatMath.sin(angle));
	// E.setValue(1, 1, FloatMath.cos(angle));
	// }
	// return E;
	// }
	//
	// public static RedMatrix newSkewMatrix(double... skew_angle) {
	// RedMatrix E = RedMatrix.newIdentityMatrix(3);
	// E.setValue(1, 0, FloatMath.tan(skew_angle_x));
	// E.setValue(0, 1, FloatMath.tan(skew_angle_y));
	// return E;
	// }

	public static RedMatrix newScaleMatrix (final double... scale) {
		final int n = scale.length;
		final RedMatrix E = RedMatrix.newIdentityMatrix(n);
		for (int i = 0; i < n; i++) {
			E.setValue(i, i, scale[i]);
		}
		return E;
	}

	private static double multiply_row_column (final Matrix A, final int a_row_number, final Matrix B, final int b_column_number) {
		final int a_width = A.getWidth();
		final int b_height = B.getHeight();
		if (a_width != b_height) {
			Err.reportError("WTF? " + a_width + " x " + b_height);
		}
		final int n = a_width;
		double result = 0d;
		for (int i = 0; i < n; i++) {
			result = result + A.getValue(i, a_row_number) * B.getValue(b_column_number, i);
		}
		return result;
	}

	@Override
	public int getWidth () {
		return this.w;
	}

	@Override
	public int getHeight () {
		return this.h;
	}

	public static Matrix multiply (final Matrix... matrix_list) {
		final int n = matrix_list.length;
		// Log.d("#" + n);
		if (n == 2) {
			// Log.d("simple");
			final Matrix A = matrix_list[0];
			final Matrix B = matrix_list[1];
			return multiply2(A, B);
		} else {

			final Matrix[] sub_list = sublist(matrix_list, 1, n);
			// Log.d("sub_list", sub_list.length);
			final Matrix sublist = multiply(sub_list);
			return multiply(matrix_list[0], sublist);
		}
	}

	static Matrix multiply2 (final Matrix A, final Matrix B) {
		// Log.d("AxB");
		// print("A", A);
		// print("B", B);

		final int a_height = A.getHeight();
		final int a_width = A.getWidth();

		final int b_height = B.getHeight();
		final int b_width = B.getWidth();

		if (a_width != b_height) {

			Err.reportError("B's matrix height (" + b_height + ") is not equal the A's matrix width (" + a_width + ")");
		}
		// if (b_width != result_width) {
		// Err.reportError("Resulting matrix width (" + result_width
		// + ") is not equal the B's matrix width (" + b_width + ")");
		// }

		final RedMatrix result = new RedMatrix(b_width, a_height);

		for (int row_number = 0; row_number < a_height; row_number++) {
			for (int column_number = 0; column_number < b_width; column_number++) {
				final double axb = multiply_row_column(A, row_number, B, column_number);
				result.setValue(column_number, row_number, axb);
			}
		}

		return result;

	}

	public static Matrix multiplyAxB (final Matrix A, final Matrix B, final Matrix result) {
		// Log.d("AxB");
		// print("A", A);
		// print("B", B);

		final int a_height = A.getHeight();
		final int a_width = A.getWidth();

		final int b_height = B.getHeight();
		final int b_width = B.getWidth();

		if (a_width != b_height) {

			Err.reportError("B's matrix height (" + b_height + ") is not equal the A's matrix width (" + a_width + ")");
		}
		// if (b_width != result_width) {
		// Err.reportError("Resulting matrix width (" + result_width
		// + ") is not equal the B's matrix width (" + b_width + ")");
		// }

		for (int row_number = 0; row_number < a_height; row_number++) {
			for (int column_number = 0; column_number < b_width; column_number++) {
				final double axb = multiply_row_column(A, row_number, B, column_number);
				result.setValue(column_number, row_number, axb);
			}
		}

		return result;

	}

	@Override
	public void setValue (final Matrix tmp) {
		for (int i = 0; i < this.w; i++) {
			for (int k = 0; k < this.h; k++) {
				this.setValue(i, k, tmp.getValue(i, k));
			}
		}
	}

	private static Matrix[] sublist (final Matrix[] matrix_list, final int from, final int to) {
		final int n = to - from;
		final Matrix[] matrix_sub_list = new Matrix[n];
		for (int i = 0; i < n; i++) {
			matrix_sub_list[i] = matrix_list[from + i];
		}
		return matrix_sub_list;
	}

	public static void print (final String matrix_name, final RedMatrix matrix) {
		L.d("---Matrix[" + matrix.getWidth() + "x" + matrix.getHeight() + "] <" + matrix_name + ">---");

		for (int k = 0; k < matrix.getHeight(); k++) {
			L.d("   ", matrix_row_string(matrix, k));

		}
		L.d("---<" + matrix_name + ">-END---");

	}

	private static String matrix_row_string (final RedMatrix matrix, final int row) {
		String result = "[";
		for (int k = 0; k < matrix.getWidth();) {
			result = result + matrix_value_to_string(matrix.getValue(k, row)) + "]";
			k++;
			if (k != matrix.getWidth()) {
				result = result + " [";
			}
		}
		return result;
	}

	private static String matrix_value_to_string (final double val) {
		// DecimalFormat df = new DecimalFormat("0.00000");
		// return df.format(val);
		return FloatMath.roundToDigit(val, 4) + "";
	}

	@Override
	public void print (final String string) {
		print(string, this);

	}

	public static Matrix inverseOf (final Matrix matrix) {
		final int w = matrix.getWidth();
		final int h = matrix.getHeight();

		if (w != h) {
			Err.reportError("This is not square matrix!");
		}

		final Matrix A = new RedMatrix(w, w);
		A.setValue(matrix);

		final Matrix E = newIdentityMatrix(w);
		// A.print("input");
		for (int i = 0; i < w; i++) {
			resolve_column_to_diagonal_form(A, E, i);
		}
		// A.print("diaginal");

		// Matrix DmE = RedMatrix.multiply(A, E);
		// DmE.print("DmE");
		// Matrix EmD = RedMatrix.multiply(E, A);
		// EmD.print("EmD");

		for (int i = 0; i < w; i++) {
			resolve_diagonal(A, E, i);
			// A.print("resolve_diagonal A");
			// E.print("resolve_diagonal E");
		}

		return E;
	}

	private static void resolve_diagonal (final Matrix a, final Matrix e, final int row) {

		final double diagonal_element = a.getValue(row, row);
		for (int i = 0; i < a.getWidth(); i++) {
			{
				final double a_element = a.getValue(i, row);
				a.setValue(i, row, a_element / diagonal_element);
			}
			{
				final double e_element = e.getValue(i, row);
				e.setValue(i, row, e_element / diagonal_element);
			}
		}

	}

	private static void resolve_column_to_diagonal_form (final Matrix a, final Matrix e, final int diaginal_position) {
		double lead_element = a.getValue(diaginal_position, diaginal_position);
		if (FloatMath.isWithinEpsilon(lead_element)) {
			lead_element = fix_lead_element(a, e, diaginal_position);
		}

		for (int i = 0; i < a.getHeight(); i++) {
			if (i != diaginal_position) {
				final double element_to_dispose = a.getValue(diaginal_position, i);
				if (!FloatMath.isWithinEpsilon(element_to_dispose)) {
					final double multiplier = -1d * element_to_dispose / lead_element;
					final int source_row = diaginal_position;
					final int target_row = i;
					add_row(source_row, target_row, a, 0, multiplier);
					add_row(source_row, target_row, e, 0, multiplier);
					// L.d("---------------------------------");
					// L.d("multiplier", multiplier);
					// a.print("A [" + diaginal_position + ";" + i + "]");
					// e.print("E [" + diaginal_position + ";" + i + "]");
				}
			}
		}

	}

	private static double fix_lead_element (final Matrix a, final Matrix e, final int column) {
		final int n = a.getHeight();
		if (n == 1) {
			Err.reportError("This is not a reversable matrix.");
		}
		double lead_element = 0;
		for (int i = column + 1; i < n; i++) {
			lead_element = a.getValue(column, i);
			if (!FloatMath.isWithinEpsilon(lead_element)) {
				final int source_row = i;
				final int target_row = column;
				add_row(source_row, target_row, a, column, 1d);
				add_row(source_row, target_row, e, column, 1d);
				// a.print("fix lead A");
				// e.print("fix lead E");
				return lead_element;
			}
		}
		a.print("BAD MATRIX");
		Err.reportError("This is not a reversable matrix.");
		return 0;
	}

	private static void add_row (final int source_row, final int target_row, final Matrix A,
		final int from_column_and_to_the_right, final double multiplier) {
		for (int i = from_column_and_to_the_right; i < A.getWidth(); i++) {
			final double source_value = A.getValue(i, source_row);
			final double current_value = A.getValue(i, target_row);
			A.setValue(i, target_row, current_value + source_value * multiplier);
		}
	}

	public static void inverse (final Matrix matrix, final Matrix inverseAresult) {
		final int w = matrix.getWidth();
		final int h = matrix.getHeight();

		if (w != h) {
			Err.reportError("This is not square matrix!");
		}

		final Matrix A = new RedMatrix(w, w);
		A.setValue(matrix);

		inverseAresult.resetToIdentityMatrix();

		// A.print("input");
		for (int i = 0; i < w; i++) {
			resolve_column_to_diagonal_form(A, inverseAresult, i);
		}
		// A.print("diaginal");

		// Matrix DmE = RedMatrix.multiply(A, E);
		// DmE.print("DmE");
		// Matrix EmD = RedMatrix.multiply(E, A);
		// EmD.print("EmD");

		for (int i = 0; i < w; i++) {
			resolve_diagonal(A, inverseAresult, i);
			// A.print("resolve_diagonal A");
			// E.print("resolve_diagonal E");
		}

	}

	@Override
	public void resetToIdentityMatrix () {
		for (int j = 0; j < this.h; j++) {
			for (int i = 0; i < this.w; i++) {
				if (i == j) {
					this.setValue(i, j, 1d);
				} else {
					this.setValue(i, j, 0d);
				}
			}
		}
	}

	@Override
	public boolean isSquare () {
		return this.w == this.h;
	}

	@Override
	public void resetToZeroMatrix () {
		for (int j = 0; j < this.h; j++) {
			for (int i = 0; i < this.w; i++) {
				this.setValue(i, j, 0d);
			}
		}
	}
}
