
package com.jfixby.scarabei.red.desktop.test;

import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;

public class GdxJsonTest {

	public long stamp = System.currentTimeMillis();
	String test = stamp + "x";

	public Vector<String> vector = new Vector<String>();

	GdxJsonTest other = null;

	@Test
	public void test () {
		ScarabeiDesktop.deploy();
		Json.installComponent("com.jfixby.scarabei.adopted.gdx.json.RedJson");

		L.d("GdxJsonTest");

		GdxJsonTest original = new GdxJsonTest();
		original.vector.add(System.currentTimeMillis() + "A");
		original.vector.add(System.currentTimeMillis() + "B");
		original.vector.add(System.currentTimeMillis() + "C");
		original.other = new GdxJsonTest();
		L.d("original");
		L.d(original);

		String string = Json.serializeToString(original).toString();
		GdxJsonTest restored = Json.deserializeFromString(GdxJsonTest.class, string);
		L.d();
		L.d(string);
		L.d();
		L.d("restored", restored.equals(original));

		L.d("restored");
		L.d(restored);
		assertTrue(restored.equals(original));
		assertTrue(restored.toString().equals(original.toString()));

	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)(stamp ^ (stamp >>> 32));
		result = prime * result + ((test == null) ? 0 : test.hashCode());
		result = prime * result + ((vector == null) ? 0 : vector.hashCode());
		return result;
	}

	@Override
	public boolean equals (Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		GdxJsonTest other = (GdxJsonTest)obj;
		if (stamp != other.stamp) return false;
		if (test == null) {
			if (other.test != null) return false;
		} else if (!test.equals(other.test)) return false;
		if (vector == null) {
			if (other.vector != null) return false;
		} else if (!vector.equals(other.vector)) return false;
		return true;
	}

	@Override
	public String toString () {
		return "GdxJsonTest [stamp=" + stamp + ", test=" + test + ", vector=" + vector + "]";
	}

}
