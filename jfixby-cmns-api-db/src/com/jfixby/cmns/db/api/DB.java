
package com.jfixby.cmns.db.api;

import com.jfixby.cmns.api.ComponentInstaller;

public class DB {

	static private ComponentInstaller<DBComponent> componentInstaller = new ComponentInstaller<>("DataBase");

	public static final void installComponent (final DBComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final DBComponent invoke () {
		return componentInstaller.invokeComponent();
	}

	public static final DBComponent component () {
		return componentInstaller.getComponent();
	}
}
