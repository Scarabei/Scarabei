
package com.jfixby.scarabei.api.image;

import com.jfixby.scarabei.api.color.Color;

public interface PixelByPixelAction {

	boolean /* true == break loop action */ scanPixelAt (ColorMap image, int x, int y, Color value);

}
