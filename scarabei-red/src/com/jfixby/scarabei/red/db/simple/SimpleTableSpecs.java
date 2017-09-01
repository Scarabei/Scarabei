
package com.jfixby.scarabei.red.db.simple;

import java.util.ArrayList;

public class SimpleTableSpecs {

	public String tableName;
	public final ArrayList<String> columns = new ArrayList<String>();

	public void addColumn (final String columnName) {
		this.columns.add(columnName);
	}

}
