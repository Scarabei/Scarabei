
package com.jfixby.scarabei.api.geometry.projections;

import com.jfixby.scarabei.api.floatn.Float2;

public interface Projection {

	public void project (final Float2 point);

	public void unProject (final Float2 point);

}
