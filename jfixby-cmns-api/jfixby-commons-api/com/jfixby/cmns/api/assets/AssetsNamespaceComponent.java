package com.jfixby.cmns.api.assets;

public interface AssetsNamespaceComponent {

	AssetID newAssetId(String asset_id_string);

	String SEPARATOR();

	<T> NamespaceRegistry<T> newRegistry();

	AssetID ROOT();

}
