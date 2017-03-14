
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.err.Err;
import com.jfixby.scarabei.api.floatn.Float2;
import com.jfixby.scarabei.api.floatn.ReadOnlyFloat2;
import com.jfixby.scarabei.api.geometry.Geometry;
import com.jfixby.scarabei.api.geometry.RectangleCorner;
import com.jfixby.scarabei.api.geometry.Vertex;
import com.jfixby.scarabei.api.geometry.projections.Transform2D;

public class RedVertex implements Vertex, RectangleCorner {

	private final Float2 world_position = Geometry.newFloat2();
	private final Float2 relative_position = Geometry.newFloat2();

	public RedVertex (final VertexMaster master) {
		this.master = master;
	}

	public void setRightEdge (final RedEdge new_edge) {
		this.right_edge = new_edge;
	}

	public void setLeftEdge (final RedEdge new_edge) {
		this.left_edge = new_edge;
	}

	RedEdge left_edge;
	RedEdge right_edge;
	VertexMaster master;

	@Override
	public RedEdge getRightEdge () {
		return this.right_edge;
	}

	@Override
	public RedEdge getLeftEdge () {
		return this.left_edge;
	}

	@Override
	public ReadOnlyFloat2 transformed () {
		this.world_position.set(this.relative_position);
		this.getTransform().transform(this.world_position);/// ? ISSUE?
		return this.world_position;
	}

	private Transform2D getTransform () {
		return this.master.getTransform();
	}

	@Override
	public Float2 relative () {
		return this.relative_position;
	}

	public void set (final Vertex a) {
		Err.throwNotImplementedYet();
	}

	// @Override
	// public CanvasTransform getTransform() {
	// return this.master.getTransform();
	// }

	@Override
	public String toString () {
		return "RedVertex [world_position=" + this.transformed() + "]";
	}

	@Override
	public double getX () {

		return this.transformed().getX();
	}

	@Override
	public double getY () {

		return this.transformed().getY();
	}

	@Override
	public boolean isInEpsilonDistance (final ReadOnlyFloat2 other) {

		return this.transformed().isInEpsilonDistance(other);
	}

	@Override
	public boolean isInEpsilonDistanceOfZero () {

		return this.transformed().isInEpsilonDistanceOfZero();
	}

	@Override
	public double distanceTo (final ReadOnlyFloat2 other) {
		return this.transformed().distanceTo(other);
	}

	@Override
	public double norm () {
		return this.world_position.norm();
	}

// @Override
// public Float2 getLinearSum (final double alpha, final FixedFloat2 other, final double betta, final Float2 output) {
// return output.setLinearSum(this, alpha, other, betta);
// }

}
