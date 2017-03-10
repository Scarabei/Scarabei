
package com.jfixby.scarabei.api.math;

import com.jfixby.scarabei.api.ComponentInstaller;

public class MathTools {

	static private ComponentInstaller<MathToolsComponent> componentInstaller = new ComponentInstaller<MathToolsComponent>(
		"MathTools");

	public static final void installComponent (final MathToolsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final MathToolsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final MathToolsComponent component () {
		return componentInstaller.getComponent();
	}

	public static VectorTool newVectorTool () {
		return invoke().newVectorTool();
	}

	public static Matrix newMatrix (final int w, final int h) {
		return invoke().newMatrix(w, h);
	}

	public static Matrix newIdentityMatrix (final int n) {
		return invoke().newIdentityMatrix(n);
	}

	public static Matrix inverseOf (final Matrix a) {
		return invoke().inverseOf(a);
	}

	public static Matrix multiply (final Matrix... matrix_list) {
		return invoke().multiply(matrix_list);
	}

	public static Matrix newScaleMatrix (final double... scale) {
		return invoke().newScaleMatrix(scale);
	}

	public static Matrix newOffsetMatrix (final double... offset) {
		return invoke().newOffsetMatrix(offset);
	}

	public static Matrix newRotationMatrix (final double rotation) {
		return invoke().newRotationMatrix(rotation);
	}

	public static void multiplyAxB (final Matrix A, final Matrix B, final Matrix result) {
		invoke().multiplyAxB(A, B, result);
	}

	public static void inverse (final Matrix A, final Matrix inverseAresult) {
		invoke().inverse(A, inverseAresult);
	}

	public static void setupOffsetMatrix (final Matrix matrix, final double... offset) {
		invoke().setupOffsetMatrix(matrix, offset);
	}

	public static void setupScaleMatrix (final Matrix matrix, final double... scale) {
		invoke().setupScaleMatrix(matrix, scale);
	}

	public static void setupRotationMatrix (final Matrix matrix, final double radians) {
		invoke().setupRotationMatrix(matrix, radians);
	}

	public static boolean pointLiesInsideTriangle (final double point_x, final double point_y, final double triangle_x0,
		final double triangle_y0, final double triangle_x1, final double triangle_y1, final double triangle_x2,
		final double triangle_y2) {
		return invoke().pointLiesInsideTriangle(point_x, point_y, triangle_x0, triangle_y0, triangle_x1, triangle_y1, triangle_x2,
			triangle_y2);
	}

	public static void setupSkewMatrix (final Matrix skew_matrix, final double... skew) {
		invoke().setupSkewMatrix(skew_matrix, skew);
	}

}
