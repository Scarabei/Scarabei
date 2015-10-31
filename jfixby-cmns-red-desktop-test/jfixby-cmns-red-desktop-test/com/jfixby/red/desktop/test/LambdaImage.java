package com.jfixby.red.desktop.test;

import com.jfixby.cmns.desktop.DesktopAssembler;
import com.jfixby.red.desktop.test.ExampleLambda.FunctionalInterface;

public class LambdaImage {

	public interface Image {
		float value(int x, int y);
	}

	public interface Norm {
		float value(VectorN n);
	}

	public interface VectorN {
		float value(int n);
	}

	static Norm factorial = null;
	
	public static void main(String[] args) {
		DesktopAssembler.setup();

	}

}
