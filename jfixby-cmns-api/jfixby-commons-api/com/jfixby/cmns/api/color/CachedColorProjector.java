
package com.jfixby.cmns.api.color;

public interface CachedColorProjector extends ColorProjector {

	public void clear ();

	public void setBase (ColorProjector color);
}
