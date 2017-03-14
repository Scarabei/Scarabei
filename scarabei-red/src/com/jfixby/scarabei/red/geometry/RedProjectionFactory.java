
package com.jfixby.scarabei.red.geometry;

import com.jfixby.scarabei.api.geometry.projections.ComposedProjection;
import com.jfixby.scarabei.api.geometry.projections.IdentityProjection;
import com.jfixby.scarabei.api.geometry.projections.OffsetProjection;
import com.jfixby.scarabei.api.geometry.projections.Projection;
import com.jfixby.scarabei.api.geometry.projections.ProjectionFactory;
import com.jfixby.scarabei.api.geometry.projections.RotateAndOffsetProjection;
import com.jfixby.scarabei.api.geometry.projections.RotateProjection;
import com.jfixby.scarabei.api.geometry.projections.ScaleProjection;
import com.jfixby.scarabei.api.geometry.projections.SkewProjection;

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

	@Override
	public OffsetProjection newOffset (final double x, final double y) {
		final RedOffsetProjection offset = new RedOffsetProjection();
		offset.setOffsetX(x);
		offset.setOffsetY(y);
		return offset;
	}

	@Override
	public RotateAndOffsetProjection newOffsetAndRotate () {
		final RedRotateAndOffsetProjection combo = new RedRotateAndOffsetProjection();
		return combo;
	}

	@Override
	public ScaleProjection newScale () {
		final RedScaleProjection pj = new RedScaleProjection();
		return pj;
	}

	@Override
	public SkewProjection newSkew () {
		final RedSkewProjection pj = new RedSkewProjection();
		return pj;
	}

}
