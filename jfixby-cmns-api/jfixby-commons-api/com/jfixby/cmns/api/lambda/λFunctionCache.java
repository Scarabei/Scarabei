package com.jfixby.cmns.api.lambda;

public interface λFunctionCache<A, B> {

	B get(A key);

	void put(A key, B value);

	void print(String tag);

}
