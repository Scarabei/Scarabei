
package com.jfixby.scarabei.db.api;

import com.jfixby.scarabei.api.ComponentInstaller;

public class DB {

	static private ComponentInstaller<DBComponent> componentInstaller = new ComponentInstaller<DBComponent>("DataBase");

	public static final void installComponent (final DBComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static void installComponent (final String className) {
		componentInstaller.installComponent(className);
	}

	public static final DBComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final DBComponent component () {
		return componentInstaller.getComponent();
	}

	public static DBConfig newDBConfig () {
		return invoke().newDBConfig();
	}

	public static DataBase newDB (final DBConfig config) {
		return invoke().newDB(config);
	}

}
