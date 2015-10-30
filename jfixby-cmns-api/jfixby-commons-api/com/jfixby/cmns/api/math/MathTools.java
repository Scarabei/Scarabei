package com.jfixby.cmns.api.math;

import com.jfixby.cmns.api.components.ComponentInstaller;

public class MathTools {

	static private ComponentInstaller<MathToolsComponent> componentInstaller = new ComponentInstaller<MathToolsComponent>(
			"MathTools");

	public static final void installComponent(
			MathToolsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final MathToolsComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final MathToolsComponent component() {
		return componentInstaller.getComponent();
	}

	public static VectorTool newVectorTool() {
		return invoke().newVectorTool();
	}

	public static Matrix newMatrix(int w, int h) {
		return invoke().newMatrix(w, h);
	}

	public static Matrix newIdentityMatrix(int n) {
		return invoke().newIdentityMatrix(n);
	}

	public static Matrix inverseOf(Matrix a) {
		return invoke().inverseOf(a);
	}

	public static Matrix multiply(Matrix... matrix_list) {
		return invoke().multiply(matrix_list);
	}

	public static Matrix newScaleMatrix(double... scale) {
		return invoke().newScaleMatrix(scale);
	}

	public static Matrix newOffsetMatrix(double... offset) {
		return invoke().newOffsetMatrix(offset);
	}

	public static Matrix newRotationMatrix(double rotation) {
		return invoke().newRotationMatrix(rotation);
	}

	public static void multiplyAxB(Matrix A, Matrix B, Matrix result) {
		invoke().multiplyAxB(A, B, result);
	}

	public static void inverse(Matrix A, Matrix inverseAresult) {
		invoke().inverse(A, inverseAresult);
	}

	public static void setupOffsetMatrix(Matrix matrix, double... offset) {
		invoke().setupOffsetMatrix(matrix, offset);
	}

	public static void setupScaleMatrix(Matrix matrix, double... scale) {
		invoke().setupScaleMatrix(matrix, scale);
	}

	public static void setupRotationMatrix(Matrix matrix, double radians) {
		invoke().setupRotationMatrix(matrix, radians);
	}

	public static boolean pointLiesInsideTriangle(double point_x,
			double point_y, double triangle_x0, double triangle_y0,
			double triangle_x1, double triangle_y1, double triangle_x2,
			double triangle_y2) {
		return invoke()
				.pointLiesInsideTriangle(point_x, point_y, triangle_x0,
						triangle_y0, triangle_x1, triangle_y1, triangle_x2,
						triangle_y2);
	}

	public static void setupSkewMatrix(Matrix skew_matrix, double... skew) {
		invoke().setupSkewMatrix(skew_matrix, skew);
	}

}
