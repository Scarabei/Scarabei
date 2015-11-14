package com.jfixby.cmns.api.lambda;

public interface λFunctionCache<A, B> {

	B get(A input);

	void put(A input, B value);

	void print(String tag);

}
