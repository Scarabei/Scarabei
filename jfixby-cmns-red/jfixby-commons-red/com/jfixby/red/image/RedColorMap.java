package com.jfixby.red.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.image.ColorMap;
import com.jfixby.cmns.api.image.ColorMapSpecs;
import com.jfixby.cmns.api.image.ColoredλImage;
import com.jfixby.cmns.api.image.GrayMap;
import com.jfixby.cmns.api.image.GrayλImage;

public class RedColorMap implements ColorMap {
    RedColorMap master = this;
    private ColoredλImage lambda;

    private int width;
    private int height;

    final private GrayMap alpha = new GrayMap() {

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

    };
    final private GrayMap red = new GrayMap() {

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

    };

    public RedColorMap(ColorMapSpecs lambda_specs) {
	this.width = lambda_specs.getColorMapWidth();
	this.height = lambda_specs.getColorMapHeight();
	ColoredλImage tmp_lambda = lambda_specs.getLambdaColoredImage();
	if (tmp_lambda != null) {
	    this.lambda = tmp_lambda;
	    return;
	}
	final GrayλImage green = Debug.checkNull(lambda_specs.getGreen());
	final GrayλImage red = Debug.checkNull(lambda_specs.getRed());
	final GrayλImage blue = Debug.checkNull(lambda_specs.getBlue());
	final GrayλImage alpha = defaultAlpha(lambda_specs.getAlpha());

	this.lambda = new ColoredλImage() {
	    @Override
	    public Color valueAt(final float x, final float y) {
		float a = alpha.valueAt(x, y);
		float r = red.valueAt(x, y);
		float g = green.valueAt(x, y);
		float b = blue.valueAt(x, y);
		return Colors.newColor(a, r, g, b);
	    }
	};

    }

    private GrayλImage defaultAlpha(GrayλImage alpha) {
	if (alpha == null) {
	    alpha = new GrayλImage() {
		@Override
		public float valueAt(float x, float y) {
		    return 1;
		}
	    };
	}
	return alpha;
    }

    @Override
    public String toString() {
	return "LambdaColorMap[" + width + "x" + height + "] ";
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
    public ColoredλImage getLambdaImage() {
	return lambda;
    }

    @Override
    public GrayMap getAlpha() {
	return alpha;
    }

    @Override
    public Color valueAt(float x, float y) {
	return lambda.valueAt(x, y);
    }

    @Override
    public GrayMap getRed() {
	return red;
    }

}
