
package com.jfixby.scarabei.api.geometry.projections;

public interface ProjectionFactory {

	OffsetProjection newOffset ();

	OffsetProjection newOffset (double x, double y);

	RotateProjection newRotate ();

	IdentityProjection IDENTITY ();

	ComposedProjection compose (Projection... sequence);

	RotateAndOffsetProjection newOffsetAndRotate ();

	ScaleProjection newScale ();

	SkewProjection newSkew ();

}
