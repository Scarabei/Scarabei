
package com.jfixby.cmns.api;

import com.jfixby.cmns.api.err.Err;

public class ComponentInstaller<T> {

	private T component;
	private final String name;

	public ComponentInstaller (final String component_name) {
		this.name = component_name;
	}

	public void installComponent (final T component) {
		if (this.component != null) {
			Err.reportError("Component " + this.name + " is already installed: " + this.component);
		}
		this.component = component;
		if (this.component == null) {
			Err.reportError("Component " + this.name + " is not installed. Argument is null.");
		}
	}

	public T invokeComponent () {
		if (this.component == null) {
			Err.reportError("Component " + this.name + " is not installed.");
		}
		return this.component;
	}

	public T getComponent () {
		return this.component;
	}

	public void deInstallCurrentComponent () {
		if (this.component == null) {
			Err.reportError("Component " + this.name + " is not installed.");
		}
		this.component = null;
	}

}
