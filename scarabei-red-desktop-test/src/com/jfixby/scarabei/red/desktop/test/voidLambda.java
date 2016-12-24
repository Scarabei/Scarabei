
package com.jfixby.scarabei.red.desktop.test;

public class voidLambda {

	public interface L {
		public void value ();
	}

	public static void main (final String[] args) {

		final L L = () -> System.out.println("woka");
		L.value();
	}

}
