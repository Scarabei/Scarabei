package com.jfixby.cmns.api.geometry;

import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.collections.EditableCollection;
import com.jfixby.cmns.api.collections.List;
import com.jfixby.cmns.api.components.ComponentInstaller;
import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.floatn.Float3;
import com.jfixby.cmns.api.math.Matrix;

public class Geometry {

	static private ComponentInstaller<GeometryComponent> componentInstaller = new ComponentInstaller<GeometryComponent>(
			"Geometry");

	public static final void installComponent(
			GeometryComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final GeometryComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final GeometryComponent component() {
		return componentInstaller.getComponent();
	}

	public static Rectangle newRectangle() {
		return invoke().newRectangle();
	}

	public static CombinedGeometry newCombinedGeometry() {
		return invoke().newCombinedGeometry();
	}

	public static ClosedPolygonalChain newClosedPolygonalChain() {
		return invoke().newClosedPolygonalChain();
	}

	public static Line newLine() {
		return invoke().newLine();
	}

	public static Float2 newFloat2() {
		return invoke().newFloat2();
	}

	public static Float3 newFloat3() {
		return invoke().newFloat3();
	}

	public static Float3 newFloat3(double x, double y, double z) {
		return invoke().newFloat3(x, y, z);
	}

	public static Rectangle newRectangle(Rectangle figure) {
		return invoke().newRectangle(figure);
	}

	public static Triangle newTriangle() {
		return invoke().newTriangle();
	}

	public static boolean equalFloat2Collections(
			Collection<? extends FixedFloat2> A,
			Collection<? extends FixedFloat2> B) {
		return invoke().equalPointCollections(A, B);
	}

	public static <T extends FixedFloat2> void setPointsCollectionSize(
			EditableCollection<T> A, int size) {
		invoke().setPointsCollectionSize(A, size);
	}

	public static void copyValues(Collection<? extends FixedFloat2> A,
			EditableCollection<? extends Float2> B) {
		invoke().copyValues(A, B);
	}

	public static void copyValues(Collection<? extends FixedFloat2> A,
			EditableCollection<? extends Float2> B, int offset) {
		invoke().copyValues(A, B, offset);
	}

	public static void applyTransformation(Matrix transformation,
			EditableCollection<? extends Float2> points_to_process) {
		invoke().applyTransformation(transformation, points_to_process);
	}

	public static Float2 newFloat2(FixedFloat2 dot) {
		return invoke().newFloat2(dot);
	}

	public static Float2 newFloat2(double x, double y) {
		return invoke().newFloat2(x, y);
	}

	public static void applyTransformation(Matrix transformation, Float2 tmp) {
		invoke().applyTransformation(transformation, tmp);
	}

	public static boolean isInEpsilonDistance(FixedFloat2 a, FixedFloat2 b) {
		return invoke().isInEpsilonDistance(a, b);
	}

	public static boolean isInEpsilonDistanceOfZero(FixedFloat2 point) {
		return invoke().isInEpsilonDistanceOfZero(point);

	}

	public static CanvasPosition newCanvasPosition() {
		return invoke().newCanvasPosition();
	}

	public static void parametrize(FixedFloat2 A, double progress_from_A_to_B,
			FixedFloat2 B, Float2 result) {
		invoke().parametrize(A, progress_from_A_to_B, B, result);
	}

	public static void parametrize(CanvasPosition A,
			double progress_from_A_to_B, CanvasPosition B, CanvasPosition result) {
		invoke().parametrize(A, progress_from_A_to_B, B, result);
	}

	public static Rectangle newRectangle(double w, double h) {
		return invoke().newRectangle(w, h);
	}

	public static double distance(FixedFloat2 A, FixedFloat2 B) {
		return invoke().distance(A, B);
	}

	public static Circle newCircle() {
		return invoke().newCircle();
	}

	public static void setupWrapingFrame(
			Collection<? extends FixedFloat2> points_to_wrap,
			Rectangle wrapping_frame) {
		invoke().setupWrapingFrame(points_to_wrap, wrapping_frame);
	}

	public static <T extends EditableCollection<Float3>> T newFloat3(
			T target_list, int how_many_to_add) {
		return invoke().newFloat3(target_list, how_many_to_add);
	}

	public static <T extends EditableCollection<Float2>> T newFloat2(
			T target_list, int how_many_to_add) {
		return invoke().newFloat2(target_list, how_many_to_add);
	}

	public static ClosedPolygonalChain newClosedPolygonalChain(
			Collection<Float2> vertices) {
		return invoke().newClosedPolygonalChain(vertices);
	}

	public static List<Float3> newFloat3List(int size) {
		return invoke().newFloat3List(size);
	}

	public static List<Float2> newFloat2List(int size) {
		return invoke().newFloat2List(size);
	}

	public static void fillUpFloat2(EditableCollection<Float2> output,
			int desired_size) {
		invoke().fillUpFloat2(output, desired_size);
	}

	public static void fillUpFloat3(EditableCollection<Float3> output,
			int desired_size) {
		invoke().fillUpFloat3(output, desired_size);
	}

	// public static ClosedPolygonalChain newPoly(AssetID asset_id) {
	// return invoke().newPoly(asset_id);
	// }

}
