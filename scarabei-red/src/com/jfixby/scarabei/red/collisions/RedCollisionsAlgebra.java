
package com.jfixby.scarabei.red.collisions;

import com.jfixby.scarabei.api.collisions.AtomicCategories;
import com.jfixby.scarabei.api.collisions.CollisionCategory;
import com.jfixby.scarabei.api.collisions.CollisionRelations;
import com.jfixby.scarabei.api.collisions.CollisionsComponent;

public class RedCollisionsAlgebra implements CollisionsComponent {

	private final RedAtomicCategoriesList ATOMS = new RedAtomicCategoriesList();

	@Override
	public AtomicCategories AtomicCategories () {
		return this.ATOMS;
	}

	@Override
	public CollisionCategory produceAND (final CollisionCategory A, final CollisionCategory B) {
		return RedCategory.AND((RedCategory)A, (RedCategory)B);
	}

	@Override
	public CollisionCategory produceOR (final CollisionCategory A, final CollisionCategory B) {
		return RedCategory.OR((RedCategory)A, (RedCategory)B);
	}

	@Override
	public CollisionCategory produceNOT (final CollisionCategory A) {
		return RedCategory.NOT((RedCategory)A);
	}

	@Override
	public CollisionCategory ALL () {
		return RedCategory.ALL();
	}

	@Override
	public CollisionCategory NONE () {
		return RedCategory.NONE();

	}

	@Override
	public CollisionCategory DEFAULT () {
		return RedCategory.ALL();
	}

	@Override
	public CollisionRelations newCollisionRelations () {
		return new RedCollisionRelations();
	}

}
