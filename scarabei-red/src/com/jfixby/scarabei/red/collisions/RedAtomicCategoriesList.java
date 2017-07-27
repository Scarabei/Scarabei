
package com.jfixby.scarabei.red.collisions;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.collisions.AtomicCategories;
import com.jfixby.scarabei.api.collisions.AtomicCollisionCategory;

public class RedAtomicCategoriesList implements AtomicCategories {

	private static final int NUMBER_OF_ATOMIC_CATEGORIES = 16;
	final List<RedCategory> list = Collections.newList();

	public RedAtomicCategoriesList () {
		super();

		for (int i = 0; i < NUMBER_OF_ATOMIC_CATEGORIES; i++) {
			final RedCategory category = new RedCategory(i, this);
			list.add(category);
		}
	}

	@Override
	public int size () {
		return NUMBER_OF_ATOMIC_CATEGORIES;
	}

	@Override
	public AtomicCollisionCategory getIndex (final int index) {
		return list.getElementAt(index);
	}

	@Override
	public int indexOf (final AtomicCollisionCategory atomic) {
		return this.list.indexOf(atomic);
	}

	@Override
	public AtomicCollisionCategory A () {
		return this.list.getElementAt(0);
	}

	@Override
	public AtomicCollisionCategory B () {
		return this.list.getElementAt(1);
	}

	@Override
	public AtomicCollisionCategory C () {
		return this.list.getElementAt(2);
	}

	@Override
	public AtomicCollisionCategory D () {
		return this.list.getElementAt(3);
	}

	@Override
	public AtomicCollisionCategory E () {
		return this.list.getElementAt(4);
	}

	@Override
	public AtomicCollisionCategory F () {
		return this.list.getElementAt(5);
	}

	@Override
	public AtomicCollisionCategory G () {
		return this.list.getElementAt(6);
	}

	@Override
	public AtomicCollisionCategory H () {
		return this.list.getElementAt(7);
	}

	@Override
	public AtomicCollisionCategory K () {
		return this.list.getElementAt(8);
	}

	@Override
	public AtomicCollisionCategory P () {
		return this.list.getElementAt(9);
	}

	@Override
	public AtomicCollisionCategory Q () {
		return this.list.getElementAt(10);
	}

	@Override
	public AtomicCollisionCategory R () {
		return this.list.getElementAt(11);
	}

	@Override
	public AtomicCollisionCategory S () {
		return this.list.getElementAt(12);
	}

	@Override
	public AtomicCollisionCategory U () {
		return this.list.getElementAt(13);
	}

	@Override
	public AtomicCollisionCategory M () {
		return this.list.getElementAt(14);
	}

	@Override
	public AtomicCollisionCategory N () {
		return this.list.getElementAt(15);
	}

}
