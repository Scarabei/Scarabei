package com.jfixby.red.lambda;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.lambda.λExpression;
import com.jfixby.cmns.api.lambda.λFunction;

public class RedFunction<X, Y> implements λFunction<X, Y> {

	final private λExpression<X, Y> exe;
	final private Map<X, Y> map;
	private Y value;

	public RedFunction(λExpression<X, Y> exe) {
		this.exe = exe;
		this.map = JUtils.newMap();
	}

	@Override
	public Y val(X input) {
		this.value = this.map.get(input);
		if (this.value == null) {
			this.value = this.exe.calculate(input);
			this.map.put(input, this.value);
		}
		return this.value;
	}

}
