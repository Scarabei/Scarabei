package com.jfixby.red.name;

import com.jfixby.cmns.api.assets.AssetID;
import com.jfixby.cmns.api.assets.AssetsNamespaceComponent;
import com.jfixby.cmns.api.assets.NamespaceRegistry;

public class RedAssetsNamespace implements AssetsNamespaceComponent {

	@Override
	public AssetID newAssetId(String asset_id_string) {
		return new RedAssetID(asset_id_string);
	}

	@Override
	public String SEPARATOR() {
		return ".";
	}

	@Override
	public <T> NamespaceRegistry<T> newRegistry() {
		return new RedNamespaceRegistry<T>();
	}

	@Override
	public AssetID ROOT() {
		return new RedAssetID();
	}

}
