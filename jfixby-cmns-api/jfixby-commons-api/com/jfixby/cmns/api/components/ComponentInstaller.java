package com.jfixby.cmns.api.components;

public class ComponentInstaller<T> {

	private T component;
	private String name;

	public ComponentInstaller(String component_name) {
		this.name = component_name;
	}

	public void installComponent(T component) {
		if (this.component != null) {
			throw new Error("Component " + name + " is already installed: "
					+ this.component);
		}
		this.component = component;
		if (this.component == null) {
			throw new Error("Component " + name
					+ " is not installed. Argument is null.");
		}
	}

	public T invokeComponent() {
		if (component == null) {
			throw new Error("Component " + name + " is not installed.");
		}
		return component;
	}

	public T getComponent() {
		return component;
	}

	public void deInstallCurrentComponent() {
		if (component == null) {
			throw new Error("Component " + name + " is not installed.");
		}
		component = null;
	}

}
