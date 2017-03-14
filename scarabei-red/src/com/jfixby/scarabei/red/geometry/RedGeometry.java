
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.angles.Angles;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.Float3;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.Bezier;
import com.jfixby.scarabei.api.geometry.CanvasPosition;
import com.jfixby.scarabei.api.geometry.Circle;
import com.jfixby.scarabei.api.geometry.ClosedPolygonalChain;
import com.jfixby.scarabei.api.geometry.CombinedGeometry;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.geometry.GeometryComponent;
import com.jfixby.scarabei.api.geometry.Line;
import com.jfixby.scarabei.api.geometry.LinearCombinator;
import com.jfixby.scarabei.api.geometry.Rectangle;
import com.jfixby.scarabei.api.geometry.Spline2D;
import com.jfixby.scarabei.api.geometry.Triangle;
import com.jfixby.scarabei.api.geometry.projections.ProjectionFactory;
import com.jfixby.scarabei.api.math.FloatMath;
import com.jfixby.scarabei.api.math.MathTools;
import com.jfixby.scarabei.api.math.Matrix;

public class RedGeometry implements GeometryComponent {

	public RedGeometry () {
		super();

	}

	@Override
	public Line newLine () {
		return new RedLine();
	}

	@Override
	public Rectangle newRectangle () {
		return new RedRectangle();
	}

	@Override
	public Rectangle newRectangle (final Rectangle other) {
		Debug.checkNull("argument", other);
		final Rectangle result = this.newRectangle();
		result.setWidth(other.getWidth());
		result.setHeight(other.getHeight());
		result.setOriginRelativeX(other.getOriginRelativeX());
		result.setOriginRelativeY(other.getOriginRelativeY());
		result.setPosition(other.getPosition());
		return result;
	}

	@Override
	public Circle newCircle () {
		return new RedCircle();
	}

	@Override
	public CombinedGeometry newCombinedGeometry () {
		return new RedCombinedGeometry();
	}

	@Override
	public Float2 newFloat2 () {
		return new RedPoint();
	}

	@Override
	public Line newLine (final Line other) {
		return new RedLine(other);
	}

	@Override
	public Circle newCircle (final Circle other) {
		return new RedCircle(other);
	}

	@Override
	public Triangle newTriangle () {
		return new RedTriangle();
	}

	@Override
	public <T extends ReadOnlyFloat2> void setPointsCollectionSize (final EditableCollection<T> a, final int size) {
		if (a.size() == size) {
			return;
		}
		if (a.size() < size) {
			final int needed = -a.size() + size;
			for (int i = 0; i < needed; i++) {
				a.add((T)new RedPoint());
			}
		}
		if (a.size() > size) {
			final int needed = (a.size() - size);
			for (int i = 0; i < needed; i++) {
				a.removeLast();
			}
		}
	}

	@Override
	public void copyValues (final Collection<? extends ReadOnlyFloat2> a, final EditableCollection<? extends Float2> b) {
		this.copyValues(a, b, 0);
	}

	@Override
	public void copyValues (final Collection<? extends ReadOnlyFloat2> a, final EditableCollection<? extends Float2> b,
		final int offset) {
		if (a.size() + offset > b.size()) {
			Err.reportError(
				"Not enough space in the destination array, required = " + (a.size() + offset) + ", available = " + b.size());
		}
		for (int i = 0; i < a.size(); i++) {
			b.getElementAt(i + offset).set(a.getElementAt(i));
		}
	}

	@Override
	public boolean equalPointCollections (final Collection<? extends ReadOnlyFloat2> a,
		final Collection<? extends ReadOnlyFloat2> b) {
		if (a.size() != b.size()) {
			return false;
		}
		for (int i = 0; i < a.size(); i++) {
			final ReadOnlyFloat2 vertex_a = a.getElementAt(i);
			final ReadOnlyFloat2 vertex_b = b.getElementAt(i);

			final double ax = vertex_a.getX();
			final double ay = vertex_a.getY();

			final double bx = vertex_b.getX();
			final double by = vertex_b.getY();

			if (!FloatMath.isEpsilonEqualDouble(ax, bx)) {
				return false;
			}
			if (!FloatMath.isEpsilonEqualDouble(ay, by)) {
				return false;
			}

		}
		return true;
	}

	@Override
	public void applyTransformation (final Matrix transformation, final EditableCollection<? extends Float2> points_to_process) {
		for (int i = 0; i < points_to_process.size(); i++) {
			final Float2 point = points_to_process.getElementAt(i);
			this.applyTransformation(transformation, point);
		}
	}

	@Override
	public void applyTransformation (final Matrix transformation, final Float2 point) {
		this.init();

		Matrix tmp_in = null;
		Matrix tmp_out = null;

		if (transformation.getWidth() == 3) {
			tmp_in = this.tmp3_a;
			tmp_out = this.tmp3_b;
		}
		if (transformation.getWidth() == 2) {
			tmp_in = this.tmp2_a;
			tmp_out = this.tmp2_b;
		}

		tmp_in.setValue(0, 0, point.getX());
		tmp_in.setValue(0, 1, point.getY());
		this.tmp3_a.setValue(0, 2, 1);

		MathTools.multiplyAxB(transformation, tmp_in, tmp_out);

		point.setX(tmp_out.getValue(0, 0));
		point.setY(tmp_out.getValue(0, 1));

	}

	Matrix tmp3_a;
	Matrix tmp3_b;
	Matrix tmp2_a;
	Matrix tmp2_b;

	private void init () {
		if (this.tmp3_a != null) {
			return;
		}

		this.tmp3_a = MathTools.newMatrix(1, 3);
		this.tmp3_b = MathTools.newMatrix(1, 3);

		this.tmp2_a = MathTools.newMatrix(1, 2);
		this.tmp2_b = MathTools.newMatrix(1, 2);

	}

	@Override
	public Float2 newFloat2 (final ReadOnlyFloat2 dot) {
		return new RedPoint(dot);
	}

	@Override
	public boolean isInEpsilonDistance (final ReadOnlyFloat2 A, final ReadOnlyFloat2 B) {
		return FloatMath.isWithinEpsilon(FloatMath.distance(A.getX(), A.getY(), B.getX(), B.getY()));
	}

	@Override
	public boolean isInEpsilonDistanceOfZero (final ReadOnlyFloat2 A) {
		return FloatMath.isWithinEpsilon(FloatMath.distance(A.getX(), A.getY(), 0, 0));
	}

	@Override
	public CanvasPosition newCanvasPosition () {
		return new RedPosition();
	}

	@Override
	public void parametrize (final ReadOnlyFloat2 a, final double progress_from_A_to_B, final ReadOnlyFloat2 b,
		final Float2 result) {
		result.setX(a.getX() + (b.getX() - a.getX()) * progress_from_A_to_B);
		result.setY(a.getY() + (b.getY() - a.getY()) * progress_from_A_to_B);
	}

	@Override
	public void parametrize (final CanvasPosition a, final double progress_from_A_to_B, final CanvasPosition b,
		final CanvasPosition result) {
		result.setX(a.getX() + (b.getX() - a.getX()) * progress_from_A_to_B);
		result.setY(a.getY() + (b.getY() - a.getY()) * progress_from_A_to_B);
		Angles.parametrize(a.getRotation(), progress_from_A_to_B, b.getRotation(), result.getRotation());

	}

	@Override
	public Rectangle newRectangle (final double w, final double h) {
		final Rectangle rect = new RedRectangle();
		rect.setHeight(h);
		rect.setWidth(w);
		return rect;
	}

	@Override
	public double distance (final ReadOnlyFloat2 A, final ReadOnlyFloat2 B) {
		return FloatMath.distance(A.getX(), A.getY(), B.getX(), B.getY());
	}

	@Override
	public Float2 newFloat2 (final double x, final double y) {
		return new RedPoint(x, y);
	}

	@Override
	public ClosedPolygonalChain newClosedPolygonalChain () {
		return new RedClosedPolygonalChain();
	}

	@Override
	public Float3 newFloat3 () {
		return new RedPoint3(0, 0, 0);
	}

	@Override
	public Float3 newFloat3 (final double x, final double y, final double z) {
		return new RedPoint3(x, y, z);
	}

	@Override
	public Rectangle setupWrapingFrame (final Collection<? extends ReadOnlyFloat2> points_to_wrap,
		final Rectangle wrapping_frame) {
		if (points_to_wrap.size() == 0) {
			Err.reportError("Empty collection!");
		}
		final Float2 top_left = Geometry.newFloat2(points_to_wrap.getElementAt(0));
		final Float2 bottom_right = Geometry.newFloat2(points_to_wrap.getElementAt(0));
		for (int i = 1; i < points_to_wrap.size(); i++) {
			final ReadOnlyFloat2 element = points_to_wrap.getElementAt(i);
			top_left.setX(FloatMath.min(element.getX(), top_left.getX()));
			top_left.setY(FloatMath.min(element.getY(), top_left.getY()));
			bottom_right.setX(FloatMath.max(element.getX(), bottom_right.getX()));
			bottom_right.setY(FloatMath.max(element.getY(), bottom_right.getY()));
		}
		final double width = bottom_right.getX() - top_left.getX();
		final double height = bottom_right.getY() - top_left.getY();
		wrapping_frame.setRotation(0);
		wrapping_frame.setSize(width, height);
		wrapping_frame.setOriginRelativeX(-top_left.getX() / width);
		wrapping_frame.setOriginRelativeY(-top_left.getY() / height);
		wrapping_frame.setPosition(0, 0);

		return wrapping_frame;
	}

	@Override
	public <T extends EditableCollection<Float3>> T newFloat3 (final T target_list, final int how_many_to_add) {
		Debug.checkNull("target_list", target_list);
		for (int i = 0; i < how_many_to_add; i++) {
			target_list.add(this.newFloat3());
		}
		return target_list;
	}

	@Override
	public <T extends EditableCollection<Float2>> T newFloat2 (final T target_list, final int how_many_to_add) {
		Debug.checkNull("target_list", target_list);
		for (int i = 0; i < how_many_to_add; i++) {
			target_list.add(this.newFloat2());
		}
		return target_list;
	}

	@Override
	public ClosedPolygonalChain newClosedPolygonalChain (final Collection<Float2> vertices) {
		final ClosedPolygonalChain chain = this.newClosedPolygonalChain();
		chain.setSize(vertices.size());
		for (int i = 0; i < vertices.size(); i++) {
			final Float2 vi = vertices.getElementAt(i);
			chain.getVertex(i).relative().set(vi);
		}
		return chain;
	}

	@Override
	public List<Float3> newFloat3List (final int size) {
		final List<Float3> newList = Collections.newList();
		return this.newFloat3(newList, size);
	}

	@Override
	public List<Float2> newFloat2List (final int size) {
		final List<Float2> newList = Collections.newList();
		return this.newFloat2(newList, size);
	}

	@Override
	public void fillUpFloat2 (final EditableCollection<Float2> output, final int desired_size) {
		for (int i = output.size(); i < desired_size; i++) {
			output.add(this.newFloat2());
		}
	}

	@Override
	public void fillUpFloat3 (final EditableCollection<Float3> output, final int desired_size) {
		for (int i = output.size(); i < desired_size; i++) {
			output.add(this.newFloat3());
		}
	}

	@Override
	public ProjectionFactory getProjectionFactory () {
		return this.projectionFactory;
	}

	final RedProjectionFactory projectionFactory = new RedProjectionFactory();

	@Override
	public LinearCombinator<CanvasPosition> newLinearCanvasPositionCombinator () {
		return new RedLinearCanvasPositionCombinator();
	}

	@Override
	public Bezier<CanvasPosition> combine (final Bezier<CanvasPosition> start, final Bezier<CanvasPosition> end) {
		final LinearCombinator<CanvasPosition> comb = Geometry.newLinearCanvasPositionCombinator();
		return new Bezier<CanvasPosition>() {
			@Override
			public CanvasPosition valueAt (final double t) {
				comb.setA(start.valueAt(t));
				comb.setB(end.valueAt(t));
				return comb.getLinearCombination(1 - t, t);
			}
		};
	}

	@Override
	public Spline2D newSpline2D () {
		return new RedSpline2D();
	}

}
