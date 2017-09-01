
package com.jfixby.scarabei.db.realm;

import java.util.ArrayList;

import io.realm.RealmModel;

public class RealmDBEntry implements RealmModel {
	public ArrayList<Object> values = new ArrayList<>();

	public String getString (final int i) {
		return this.values.get(i) + "";
	}

}
