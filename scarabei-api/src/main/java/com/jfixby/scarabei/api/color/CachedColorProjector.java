
package com.jfixby.scarabei.api.color;

public interface CachedColorProjector extends ColorProjector {

	public void clear ();

	public void setBase (ColorProjector color);
}
