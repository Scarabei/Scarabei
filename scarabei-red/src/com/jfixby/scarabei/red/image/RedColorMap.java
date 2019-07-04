
package com.jfixby.scarabei.red.image;

import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.image.ColorMap;
import com.jfixby.scarabei.api.image.ColoredLambdaImage;
import com.jfixby.scarabei.api.image.GrayMap;
import com.jfixby.scarabei.api.image.GrayLambdaImage;

public class RedColorMap implements ColorMap {
	RedColorMap master = this;
	private ColoredLambdaImage lambda;

	private int width;
	private int height;

	final private GrayMap alpha = new GrayMap() {

		@Override
		public int getWidth () {
			return master.getWidth();
		}

		@Override
		public int getHeight () {
			return master.getHeight();
		}

		@Override
		public GrayLambdaImage getLambdaImage () {
			return master.alpha;
		}

		@Override
		public float valueAt (float x, float y) {
			return master.valueAt(x, y).alpha();
		}

	};
	final private GrayMap red = new GrayMap() {

		@Override
		public int getWidth () {
			return master.getWidth();
		}

		@Override
		public int getHeight () {
			return master.getHeight();
		}

		@Override
		public GrayLambdaImage getLambdaImage () {
			return master.red;
		}

		@Override
		public float valueAt (float x, float y) {
			return master.valueAt(x, y).red();
		}

	};

	final private GrayMap green = new GrayMap() {

		@Override
		public int getWidth () {
			return master.getWidth();
		}

		@Override
		public int getHeight () {
			return master.getHeight();
		}

		@Override
		public GrayLambdaImage getLambdaImage () {
			return master.green;
		}

		@Override
		public float valueAt (float x, float y) {
			return master.valueAt(x, y).green();
		}

	};

	final private GrayMap blue = new GrayMap() {

		@Override
		public int getWidth () {
			return master.getWidth();
		}

		@Override
		public int getHeight () {
			return master.getHeight();
		}

		@Override
		public GrayLambdaImage getLambdaImage () {
			return master.blue;
		}

		@Override
		public float valueAt (float x, float y) {
			return master.valueAt(x, y).blue();
		}

	};

	public RedColorMap (ColoredLambdaImage lambda, int width, int height) {
		this.width = width;
		this.height = height;
		this.lambda = lambda;
	}

	@Override
	public String toString () {
		return "LambdaColorMap[" + width + "x" + height + "] ";
	}

	@Override
	public int getWidth () {
		return this.width;
	}

	@Override
	public int getHeight () {
		return this.height;
	}

	@Override
	public ColoredLambdaImage getLambdaImage () {
		return lambda;
	}

	@Override
	public GrayMap getAlpha () {
		return alpha;
	}

	@Override
	public Color valueAt (float x, float y) {
		return lambda.valueAt(x, y);
	}

	@Override
	public GrayMap getRed () {
		return red;
	}

	@Override
	public GrayMap getGreen () {
		return green;
	}

	@Override
	public GrayMap getBlue () {
		return blue;
	}

}
