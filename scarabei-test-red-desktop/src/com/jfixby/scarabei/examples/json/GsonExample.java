
package com.jfixby.scarabei.examples.json;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.examples.json.ExempleObjectClass.ChildB;
import com.jfixby.scarabei.gson.GoogleGson;

public class GsonExample {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();
		Json.installComponent(new GoogleGson());
// Json.installComponent("com.jfixby.scarabei.gson.GoogleGson"); - dynamic class linking
// Json.installComponent(new RedGdxJson()); - an alternative implementation

		final ExempleObjectClass testObject = GsonExample.newExempleObjectClass();

		final JsonString testStringA = Json.serializeToString(testObject);
		L.d("testStringA", testStringA);

		final ExempleObjectClass testObjectRetored = Json.deserializeFromString(ExempleObjectClass.class, testStringA);

		L.d("testObject = testObjectRetored? : " + testObjectRetored.equals(testObject));

	}

	private static ExempleObjectClass newExempleObjectClass () {
		final ExempleObjectClass testObject = new ExempleObjectClass();
		testObject.Ca.v1.add("x");
		testObject.Ca.v1.add("y");
		testObject.Ca.v1.add("z");
		testObject.Cc.v3.add(new ChildB("b1"));
		testObject.Cc.v3.add(new ChildB("b2"));
		testObject.Cc.v3.add(new ChildB("b3"));

		return testObject;
	}

}
