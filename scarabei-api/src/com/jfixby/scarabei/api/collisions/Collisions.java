
package com.jfixby.scarabei.api.collisions;

import com.jfixby.scarabei.api.ComponentInstaller;

public class Collisions {

	static private ComponentInstaller<CollisionsComponent> componentInstaller = new ComponentInstaller<CollisionsComponent>(
		"Collisions");

	public static final void installComponent (final CollisionsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final CollisionsComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final CollisionsComponent component () {
		return componentInstaller.getComponent();
	}

	public static final CollisionCategory ALL () {
		return invoke().ALL();
	}

	public static final AtomicCategories AtomicCategories () {
		return invoke().AtomicCategories();
	}

	public static final CollisionCategory produceAND (final CollisionCategory A, final CollisionCategory B) {
		return invoke().produceAND(A, B);
	}

	public static final CollisionCategory produceOR (final CollisionCategory A, final CollisionCategory B) {
		return invoke().produceOR(A, B);
	}

	public static final CollisionCategory produceNOT (final CollisionCategory A) {
		return invoke().produceNOT(A);
	}

	public static final CollisionCategory NONE () {
		return invoke().NONE();
	}

	public static CollisionCategory DEFAULT () {
		return invoke().DEFAULT();
	}

	public static CollisionRelations newCollisionRelations () {
		return invoke().newCollisionRelations();
	}

}
