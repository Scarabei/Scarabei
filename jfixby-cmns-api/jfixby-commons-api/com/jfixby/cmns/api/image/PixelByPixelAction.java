
package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;

public interface PixelByPixelAction {

	boolean /* true == break loop action */ scanPixelAt (ColorMap image, int x, int y, Color value);

}
