package com.jfixby.red.geometry;

import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.CanvasTransform;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.geometry.RectangleCorner;
import com.jfixby.cmns.api.geometry.Vertex;

public class RedVertex implements Vertex, RectangleCorner {

	private final Float2 world_position = Geometry.newFloat2();
	private final Float2 relative_position = Geometry.newFloat2();

	public RedVertex(VertexMaster master) {
		this.master = master;
	}

	public void setRightEdge(RedEdge new_edge) {
		this.right_edge = new_edge;
	}

	public void setLeftEdge(RedEdge new_edge) {
		this.left_edge = new_edge;
	}

	RedEdge left_edge;
	RedEdge right_edge;
	VertexMaster master;

	@Override
	public RedEdge getRightEdge() {
		return right_edge;
	}

	@Override
	public RedEdge getLeftEdge() {
		return left_edge;
	}

	@Override
	public FixedFloat2 transformed() {
		this.world_position.set(relative_position);
		this.getTransform().transform(world_position);
		return world_position;
	}

	@Override
	public Float2 relative() {
		return relative_position;
	}

	public void set(Vertex a) {
		throw new Error();
	}

	@Override
	public CanvasTransform getTransform() {
		return this.master.getTransform();
	}

	@Override
	public String toString() {
		return "RedVertex [world_position=" + transformed() + "]";
	}

	@Override
	public double getX() {

		return this.transformed().getX();
	}

	@Override
	public double getY() {

		return this.transformed().getY();
	}

	@Override
	public boolean isInEpsilonDistance(FixedFloat2 other) {

		return this.transformed().isInEpsilonDistance(other);
	}

	@Override
	public boolean isInEpsilonDistanceOfZero() {

		return this.transformed().isInEpsilonDistanceOfZero();
	}

	@Override
	public double distanceTo(FixedFloat2 other) {
		return this.transformed().distanceTo(other);
	}

}
