
package com.jfixby.cmns.api.geometry;

public interface ProjectionFactory {

	OffsetProjection newOffset ();

	IdentityProjection IDENTITY ();

}
