package com.jfixby.cmns.api.lambda;

public interface λFunctionCache<X, Y> {

	Y get(X key);

	void put(X key, Y value);

	void print(String tag);

}
