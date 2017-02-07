
package com.jfixby.scarabei.api.geometry;

import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.collections.EditableCollection;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.Float3;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.math.Matrix;

public interface GeometryComponent {

	public Float2 newFloat2 ();

	public Line newLine ();

	public Line newLine (Line other);

	public Rectangle newRectangle ();

	public Circle newCircle ();

	public Circle newCircle (Circle other);

	public CombinedGeometry newCombinedGeometry ();

	public Rectangle newRectangle (Rectangle figure);

	public Triangle newTriangle ();

	public boolean equalPointCollections (Collection<? extends ReadOnlyFloat2> a, Collection<? extends ReadOnlyFloat2> b);

	public <T extends ReadOnlyFloat2> void setPointsCollectionSize (EditableCollection<T> a, int size);

	public void copyValues (Collection<? extends ReadOnlyFloat2> a, EditableCollection<? extends Float2> b);

	public void copyValues (Collection<? extends ReadOnlyFloat2> a, EditableCollection<? extends Float2> b, int offset);

	public void applyTransformation (Matrix transformation, EditableCollection<? extends Float2> points_to_process);

	void applyTransformation (Matrix transformation, Float2 point);

	public Float2 newFloat2 (ReadOnlyFloat2 dot);

	public boolean isInEpsilonDistance (ReadOnlyFloat2 a, ReadOnlyFloat2 b);

	public boolean isInEpsilonDistanceOfZero (ReadOnlyFloat2 point);

	public CanvasPosition newCanvasPosition ();

	public void parametrize (ReadOnlyFloat2 a, double progress_from_A_to_B, ReadOnlyFloat2 b, Float2 result);

	public void parametrize (CanvasPosition a, double progress_from_A_to_B, CanvasPosition b, CanvasPosition result);

	public Rectangle newRectangle (double w, double h);

	public double distance (ReadOnlyFloat2 a, ReadOnlyFloat2 b);

	public Float2 newFloat2 (double x, double y);

	public ClosedPolygonalChain newClosedPolygonalChain ();

	public Rectangle setupWrapingFrame (Collection<? extends ReadOnlyFloat2> points_to_wrap, Rectangle wrapping_frame);

	public Float3 newFloat3 (double x, double y, double z);

	public Float3 newFloat3 ();

	public <T extends EditableCollection<Float3>> T newFloat3 (T target_list, int how_many_to_add);

	public <T extends EditableCollection<Float2>> T newFloat2 (T target_list, int how_many_to_add);

	public ClosedPolygonalChain newClosedPolygonalChain (Collection<Float2> vertices);

	public List<Float3> newFloat3List (int size);

	public List<Float2> newFloat2List (int size);

	public void fillUpFloat2 (EditableCollection<Float2> output, int desired_size);

	public void fillUpFloat3 (EditableCollection<Float3> output, int desired_size);

	public ProjectionFactory getProjectionFactory ();

	public LinearCombinator<CanvasPosition> newLinearCanvasPositionCombinator ();

	public Bezier<CanvasPosition> combine (Bezier<CanvasPosition> pStart, Bezier<CanvasPosition> pEnd);

}
