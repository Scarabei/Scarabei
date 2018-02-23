
package com.jfixby.scarabei.api.geometry.projections;

import com.jfixby.scarabei.api.geometry.Rectangle;

public interface ProjectionFactory {

	OffsetProjection newOffset ();

	OffsetProjection newOffset (double x, double y);

	RotateProjection newRotate ();

	IdentityProjection IDENTITY ();

	ComposedProjection compose (Projection... sequence);

	RotateAndOffsetProjection newOffsetAndRotate ();

	RotateAndOffsetProjection newOffsetAndRotate (Rectangle rectangle);

	ScaleProjection newScale ();

	SkewProjection newSkew ();

}
