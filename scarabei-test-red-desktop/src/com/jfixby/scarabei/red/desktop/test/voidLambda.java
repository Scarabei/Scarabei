
package com.jfixby.scarabei.red.desktop.test;

public class voidLambda {

	public interface Lam {
		public void value ();
	}

	public static void main (final String[] args) {

		final Lam L = () -> System.out.println("woka");
		L.value();
	}

}
