package com.jfixby.cmns.api.lambda;

public interface λGraph<A, B> {

	B get(A input);

	void put(A input, B value);

	void print(String tag);

}
