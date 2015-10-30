package com.jfixby.cmns.api.collisions;

public interface CollisionsComponent {

	public AtomicCategories AtomicCategories();

	public CollisionCategory produceAND(CollisionCategory A, CollisionCategory B);

	public CollisionCategory produceOR(CollisionCategory A, CollisionCategory B);

	public CollisionCategory produceNOT(CollisionCategory A);

	public CollisionCategory ALL();

	public CollisionCategory NONE();

	public CollisionCategory DEFAULT();

}
