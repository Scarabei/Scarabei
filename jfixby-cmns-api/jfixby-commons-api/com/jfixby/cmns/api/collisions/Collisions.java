package com.jfixby.cmns.api.collisions;

import com.jfixby.cmns.api.components.ComponentInstaller;

public class Collisions {

	static private ComponentInstaller<CollisionsComponent> componentInstaller = new ComponentInstaller<CollisionsComponent>(
			"Collisions");

	public static final void installComponent(
			CollisionsComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final CollisionsComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final CollisionsComponent component() {
		return componentInstaller.getComponent();
	}

	public static final CollisionCategory ALL() {
		return invoke().ALL();
	}

	public static final AtomicCategories AtomicCategories() {
		return invoke().AtomicCategories();
	}

	public static final CollisionCategory produceAND(CollisionCategory A,
			CollisionCategory B) {
		return invoke().produceAND(A, B);
	}

	public static final CollisionCategory produceOR(CollisionCategory A,
			CollisionCategory B) {
		return invoke().produceOR(A, B);
	}

	public static final CollisionCategory produceNOT(CollisionCategory A) {
		return invoke().produceNOT(A);
	}

	public static final CollisionCategory NONE() {
		return invoke().NONE();
	}

	public static CollisionCategory DEFAULT() {
		return invoke().DEFAULT();
	}

}
