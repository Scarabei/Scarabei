
package com.jfixby.scarabei.red.collisions;

import com.jfixby.scarabei.api.collisions.COLLISION_RELATION;
import com.jfixby.scarabei.api.collisions.CollisionCategory;
import com.jfixby.scarabei.api.collisions.CollisionRelations;

public class RedCollisionRelations implements CollisionRelations {

	final RedFilterMask filter_mask = new RedFilterMask();

	public RedCollisionRelations () {
		super();
	}

	@Override
	public long getMaskBits () {
		return this.filter_mask.getBits();
	}

	@Override
	public void setPolicy (final COLLISION_RELATION relation, final CollisionCategory category) {
		this.setPolicy(relation, (RedCategory)category);
	}

	private void setPolicy (final COLLISION_RELATION relation, final RedCategory category) {
		if (COLLISION_RELATION.COLLIDES_WITH == relation) {
			this.filter_mask.acceptCollisionsWith(category);
		}
		if (COLLISION_RELATION.DOES_NOT_COLLIDE_WITH == relation) {
			this.filter_mask.rejectCollisionsWith(category);
		}

		// Log.d("" + relation, category);
		// Log.d(" ", filter_mask);
	}

	public void setValues (final RedCollisionRelations collisionRelations) {
		this.filter_mask.setBits(collisionRelations.filter_mask.getBits());
	}

	@Override
	public void setValues (final CollisionRelations other) {
		this.setValues((RedCollisionRelations)other);
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.filter_mask == null) ? 0 : this.filter_mask.hashCode());
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final RedCollisionRelations other = (RedCollisionRelations)obj;
		if (this.filter_mask == null) {
			if (other.filter_mask != null) {
				return false;
			}
		} else if (!this.filter_mask.equals(other.filter_mask)) {
			return false;
		}
		return true;
	}

}
