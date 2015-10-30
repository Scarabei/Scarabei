package com.jfixby.red.desktop.test;

import com.jfixby.cmns.api.log.L;

public class ExampleUsualJava {

	// Calculating factorial using usual Java interface

	public interface FunctionalInterface<Input, Output> {
		Output apply(Input input);
	}

	static FunctionalInterface<Long, Long> factorial = null;

	public static void main(String[] args) {
		Setup.setup();

		factorial = new FunctionalInterface<Long, Long>() {
			@Override
			public Long apply(Long n) {
				if (n == 0) {
					return 1L;
				}
				return n * factorial.apply(n - 1);
			}
		};

		L.d(factorial.apply(5L));

	}
}
