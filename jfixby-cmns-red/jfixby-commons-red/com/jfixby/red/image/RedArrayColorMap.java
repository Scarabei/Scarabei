package com.jfixby.red.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.color.CustomColor;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;
import com.jfixby.cmns.api.image.ColoredλImage;
import com.jfixby.cmns.api.image.EditableGrayMap;
import com.jfixby.cmns.api.image.GrayMap;
import com.jfixby.cmns.api.image.GrayλImage;
import com.jfixby.cmns.api.math.FloatMath;

public class RedArrayColorMap implements ArrayColorMap {
    RedArrayColorMap master = this;

    public RedArrayColorMap(ArrayColorMapSpecs specs) {
	this(specs.getWidth(), specs.getHeight(), specs.getDefaultColor(), new ArraySupply(specs));
    }

    static public int toInt(double x) {
	return (int) FloatMath.round(x);
    }

    final private ColoredλImage COLORED = new ColoredλImage() {
	@Override
	public Color valueAt(float x, float y) {
	    return master.valueAt(x, y);
	}
    };

    @Override
    public ColoredλImage getLambdaImage() {
	return COLORED;
    }

    final private int width;
    final private int height;
    final private Color default_color;
    private final ArraySupply supply;
    final private EditableGrayMap alpha = new EditableGrayMap() {

	@Override
	public int getWidth() {
	    return master.getWidth();
	}

	@Override
	public int getHeight() {
	    return master.getHeight();
	}

	@Override
	public GrayλImage getLambdaImage() {
	    return master.alpha;
	}

	@Override
	public float valueAt(float x, float y) {
	    return master.valueAt(x, y).alpha();
	}

	@Override
	public void setValue(int x, int y, float grayscale_value) {
	    if (master.out_of_the_scope(x, y)) {
		return;
	    }

	    Color color_value = master.supply.get(x, y);
	    CustomColor color_value_custom = Colors.newColor();
	    if (color_value != null) {
		color_value_custom.setAlpha(grayscale_value);
	    }
	    master.supply.set(x, y, color_value_custom);

	}
    };

    public RedArrayColorMap(int width, int height, Color default_color, ArraySupply supply) {
	this.width = width;
	this.height = height;
	this.default_color = Debug.checkNull("default_color", default_color);
	this.supply = Debug.checkNull("supply", supply);
    }

    @Override
    public String toString() {
	return "EditableColorMap[" + width + "x" + height + "] ";
    }

    @Override
    final public void setValue(int x, int y, Color color_value) {
	if (this.out_of_the_scope(x, y)) {
	    return;
	}
	this.supply.set(x, y, color_value);
    }

    final private boolean out_of_the_scope(final float x, final float y) {
	if (x < 0) {
	    return true;
	}
	if (y < 0) {
	    return true;
	}
	if (x >= this.width) {
	    return true;
	}
	if (y >= this.height) {
	    return true;
	}
	return false;
    }

    @Override
    public int getWidth() {
	return this.width;
    }

    @Override
    public int getHeight() {
	return this.height;
    }

    @Override
    public EditableGrayMap getAlpha() {
	return alpha;
    }

    @Override
    public Color valueAt(float x, float y) {
	if (this.out_of_the_scope(x, y)) {
	    return default_color;
	}
	final Color value = supply.get(toInt(x), toInt(y));
	if (value == null) {
	    return default_color;
	}
	return value;
    }

    @Override
    public GrayMap getRed() {
	return red;
    }

    final private EditableGrayMap red = new EditableGrayMap() {

	@Override
	public int getWidth() {
	    return master.getWidth();
	}

	@Override
	public int getHeight() {
	    return master.getHeight();
	}

	@Override
	public GrayλImage getLambdaImage() {
	    return master.red;
	}

	@Override
	public float valueAt(float x, float y) {
	    return master.valueAt(x, y).red();
	}

	@Override
	public void setValue(int x, int y, float grayscale_value) {
	    if (master.out_of_the_scope(x, y)) {
		return;
	    }

	    Color color_value = master.supply.get(x, y);
	    CustomColor color_value_custom = Colors.newColor();
	    if (color_value != null) {
		color_value_custom.setRed(grayscale_value);
	    }
	    master.supply.set(x, y, color_value_custom);

	}
    };

}
