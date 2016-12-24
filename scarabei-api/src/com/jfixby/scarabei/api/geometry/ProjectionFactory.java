
package com.jfixby.scarabei.api.geometry;

public interface ProjectionFactory {

	OffsetProjection newOffset ();

	RotateProjection newRotate ();

	IdentityProjection IDENTITY ();

	ComposedProjection compose (Projection... sequence);

}
