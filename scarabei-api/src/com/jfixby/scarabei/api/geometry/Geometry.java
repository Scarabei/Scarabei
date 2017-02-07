
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.ComponentInstaller;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.floatn.FixedFloat2;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.Float3;
import com.jfixby.scarabei.api.math.Matrix;

public class Geometry {

	static private ComponentInstaller<GeometryComponent> componentInstaller = new ComponentInstaller<GeometryComponent>(
		"Geometry");

	public static final void installComponent (final GeometryComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final GeometryComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final GeometryComponent component () {
		return componentInstaller.getComponent();
	}

	public static Rectangle newRectangle () {
		return invoke().newRectangle();
	}

	public static CombinedGeometry newCombinedGeometry () {
		return invoke().newCombinedGeometry();
	}

	public static ClosedPolygonalChain newClosedPolygonalChain () {
		return invoke().newClosedPolygonalChain();
	}

	public static Line newLine () {
		return invoke().newLine();
	}

	public static Float2 newFloat2 () {
		return invoke().newFloat2();
	}

	public static Float3 newFloat3 () {
		return invoke().newFloat3();
	}

	public static Float3 newFloat3 (final double x, final double y, final double z) {
		return invoke().newFloat3(x, y, z);
	}

	public static Rectangle newRectangle (final Rectangle figure) {
		return invoke().newRectangle(figure);
	}

	public static Triangle newTriangle () {
		return invoke().newTriangle();
	}

	public static boolean equalFloat2Collections (final Collection<? extends FixedFloat2> A,
		final Collection<? extends FixedFloat2> B) {
		return invoke().equalPointCollections(A, B);
	}

	public static <T extends FixedFloat2> void setPointsCollectionSize (final EditableCollection<T> A, final int size) {
		invoke().setPointsCollectionSize(A, size);
	}

	public static void copyValues (final Collection<? extends FixedFloat2> A, final EditableCollection<? extends Float2> B) {
		invoke().copyValues(A, B);
	}

	public static void copyValues (final Collection<? extends FixedFloat2> A, final EditableCollection<? extends Float2> B,
		final int offset) {
		invoke().copyValues(A, B, offset);
	}

	public static void applyTransformation (final Matrix transformation,
		final EditableCollection<? extends Float2> points_to_process) {
		invoke().applyTransformation(transformation, points_to_process);
	}

	public static Float2 newFloat2 (final FixedFloat2 dot) {
		return invoke().newFloat2(dot);
	}

	public static Float2 newFloat2 (final double x, final double y) {
		return invoke().newFloat2(x, y);
	}

	public static void applyTransformation (final Matrix transformation, final Float2 tmp) {
		invoke().applyTransformation(transformation, tmp);
	}

	public static boolean isInEpsilonDistance (final FixedFloat2 a, final FixedFloat2 b) {
		return invoke().isInEpsilonDistance(a, b);
	}

	public static boolean isInEpsilonDistanceOfZero (final FixedFloat2 point) {
		return invoke().isInEpsilonDistanceOfZero(point);

	}

	public static CanvasPosition newCanvasPosition () {
		return invoke().newCanvasPosition();
	}

	public static void parametrize (final FixedFloat2 A, final double progress_from_A_to_B, final FixedFloat2 B,
		final Float2 result) {
		invoke().parametrize(A, progress_from_A_to_B, B, result);
	}

	public static void parametrize (final CanvasPosition A, final double progress_from_A_to_B, final CanvasPosition B,
		final CanvasPosition result) {
		invoke().parametrize(A, progress_from_A_to_B, B, result);
	}

	public static Rectangle newRectangle (final double w, final double h) {
		return invoke().newRectangle(w, h);
	}

	public static double distance (final FixedFloat2 A, final FixedFloat2 B) {
		return invoke().distance(A, B);
	}

	public static Circle newCircle () {
		return invoke().newCircle();
	}

	public static Rectangle setupWrapingFrame (final Collection<? extends FixedFloat2> points_to_wrap,
		final Rectangle wrapping_frame) {
		return invoke().setupWrapingFrame(points_to_wrap, wrapping_frame);
	}

	public static <T extends EditableCollection<Float3>> T newFloat3 (final T target_list, final int how_many_to_add) {
		return invoke().newFloat3(target_list, how_many_to_add);
	}

	public static <T extends EditableCollection<Float2>> T newFloat2 (final T target_list, final int how_many_to_add) {
		return invoke().newFloat2(target_list, how_many_to_add);
	}

	public static ClosedPolygonalChain newClosedPolygonalChain (final Collection<Float2> vertices) {
		return invoke().newClosedPolygonalChain(vertices);
	}

	public static List<Float3> newFloat3List (final int size) {
		return invoke().newFloat3List(size);
	}

	public static List<Float2> newFloat2List (final int size) {
		return invoke().newFloat2List(size);
	}

	public static void fillUpFloat2 (final EditableCollection<Float2> output, final int desired_size) {
		invoke().fillUpFloat2(output, desired_size);
	}

	public static void fillUpFloat3 (final EditableCollection<Float3> output, final int desired_size) {
		invoke().fillUpFloat3(output, desired_size);
	}

	public static ProjectionFactory getProjectionFactory () {
		return invoke().getProjectionFactory();
	}

	public static <T extends LinearCombinable<T, ? extends T>> BezierPoint<T> newBezierPoint (final T base) {
		return invoke().newBezierPoint(base);
	}

	// public static ClosedPolygonalChain newPoly(AssetID asset_id) {
	// return invoke().newPoly(asset_id);
	// }

}
