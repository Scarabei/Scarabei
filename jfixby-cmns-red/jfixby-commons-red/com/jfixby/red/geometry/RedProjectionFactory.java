
package com.jfixby.red.geometry;

import com.jfixby.cmns.api.geometry.ComposedProjection;
import com.jfixby.cmns.api.geometry.IdentityProjection;
import com.jfixby.cmns.api.geometry.OffsetProjection;
import com.jfixby.cmns.api.geometry.Projection;
import com.jfixby.cmns.api.geometry.ProjectionFactory;
import com.jfixby.cmns.api.geometry.RotateProjection;

public class RedProjectionFactory implements ProjectionFactory {

	private static final IdentityProjection E = new RedIdentityProjection();

	public RedProjectionFactory () {
	}

	@Override
	public OffsetProjection newOffset () {
		return new RedOffsetProjection();
	}

	@Override
	public IdentityProjection IDENTITY () {
		return E;
	}

	@Override
	public RotateProjection newRotate () {
		return new RedRotateProjection();
	}

	@Override
	public ComposedProjection compose (final Projection... sequence) {
		return new RedProjectionsStack(sequence);
	}

}
