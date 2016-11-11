
package com.jfixby.cmns.api.geometry;

public interface ProjectionFactory {

	OffsetProjection newOffset ();

	RotateProjection newRotate ();

	IdentityProjection IDENTITY ();

	ComposedProjection compose (Projection... sequence);

}
