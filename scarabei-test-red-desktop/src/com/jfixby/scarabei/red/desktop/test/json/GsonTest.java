
package com.jfixby.scarabei.red.desktop.test.json;

import java.util.Vector;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.json.JsonString;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.gson.GoogleGson;

public class GsonTest {

	public static void main (final String[] args) {
		ScarabeiDesktop.deploy();
		Json.installComponent(new GoogleGson());

		final GsonTest testObject = new GsonTest();
		testObject.Ca.v1.add("x");
		testObject.Ca.v1.add("y");
		testObject.Ca.v1.add("z");
		testObject.Cc.v3.add(new ChildB("b1"));
		testObject.Cc.v3.add(new ChildB("b2"));
		testObject.Cc.v3.add(new ChildB("b3"));

		final JsonString testStringA = Json.serializeToString(testObject);
		L.d("testStringA", testStringA);

	}

	public String A = "a";
	public String B = "b";
	public String C = "c";
	ChildA Ca = new ChildA();
	ChildB Cb = new ChildB();
	ChildC Cc = new ChildC();

	public static class ChildA {
		public String A = "a";
		Vector<String> v1 = new Vector<>();

	}

	public static class ChildB {
		public ChildB (final String string) {
			this.v2.addElement(string);
		}

		public ChildB () {
		}

		public String A = "a";
		public String B = "b";
		Vector<String> v2 = new Vector<>();
	}

	public static class ChildC {
		public String A = "a";
		public String B = "b";
		public String C = "c";
		Vector<ChildB> v3 = new Vector<>();

	}

}
