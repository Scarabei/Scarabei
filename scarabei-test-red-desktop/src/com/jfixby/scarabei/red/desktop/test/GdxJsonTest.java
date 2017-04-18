
package com.jfixby.scarabei.red.desktop.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.jfixby.scarabei.api.desktop.ScarabeiDesktop;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;

public class GdxJsonTest {

	public long stamp = System.currentTimeMillis();
	String test = this.stamp + "x";

	public ArrayList<String> vector = new ArrayList<String>();

	GdxJsonTest other = null;

	@Test
	public void test () {
		ScarabeiDesktop.deploy();
		Json.installComponent("com.jfixby.scarabei.adopted.gdx.json.RedJson");

		L.d("GdxJsonTest");

		final GdxJsonTest original = new GdxJsonTest();
		original.vector.add(System.currentTimeMillis() + "A");
		original.vector.add(System.currentTimeMillis() + "B");
		original.vector.add(System.currentTimeMillis() + "C");
		original.other = new GdxJsonTest();
		L.d("original");
		L.d(original);

		final String string = Json.serializeToString(original).toString();
		final GdxJsonTest restored = Json.deserializeFromString(GdxJsonTest.class, string);
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
		result = prime * result + (int)(this.stamp ^ (this.stamp >>> 32));
		result = prime * result + ((this.test == null) ? 0 : this.test.hashCode());
		result = prime * result + ((this.vector == null) ? 0 : this.vector.hashCode());
		return result;
	}

	@Override
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final GdxJsonTest other = (GdxJsonTest)obj;
		if (this.stamp != other.stamp) {
			return false;
		}
		if (this.test == null) {
			if (other.test != null) {
				return false;
			}
		} else if (!this.test.equals(other.test)) {
			return false;
		}
		if (this.vector == null) {
			if (other.vector != null) {
				return false;
			}
		} else if (!this.vector.equals(other.vector)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString () {
		return "GdxJsonTest [stamp=" + this.stamp + ", test=" + this.test + ", vector=" + this.vector + "]";
	}

}
