package com.jfixby.cmns.api.assets;

import com.jfixby.cmns.api.components.ComponentInstaller;

public class Names {

	static private ComponentInstaller<AssetsNamespaceComponent> componentInstaller = new ComponentInstaller<AssetsNamespaceComponent>(
			"Name");

	public static final void installComponent(
			AssetsNamespaceComponent component_to_install) {
		componentInstaller.installComponent(component_to_install);
	}

	public static final AssetsNamespaceComponent invoke() {
		return componentInstaller.invokeComponent();
	}

	public static final AssetsNamespaceComponent component() {
		return componentInstaller.getComponent();
	}

	public static AssetID newAssetID(String asset_id_string) {
		return invoke().newAssetId(asset_id_string);
	}

	public static String SEPARATOR() {
		return invoke().SEPARATOR();
	}

	public static <T> NamespaceRegistry<T> newRegistry() {
		return invoke().newRegistry();
	}

	public static AssetID ROOT() {
		return invoke().ROOT();
	}

}
