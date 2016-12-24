
package com.jfixby.scarabei.red.name;

import com.jfixby.scarabei.api.assets.AssetsNamespaceComponent;
import com.jfixby.scarabei.api.assets.ID;
import com.jfixby.scarabei.api.assets.NamespaceRegistry;
import com.jfixby.scarabei.api.collections.Collection;
import com.jfixby.scarabei.api.util.JUtils;

public class RedAssetsNamespace implements AssetsNamespaceComponent {

	@Override
	public ID newID (final String asset_id_string) {
		return new RedAssetID(asset_id_string);
	}

	@Override
	public String SEPARATOR () {
		return ".";
	}

	@Override
	public <T> NamespaceRegistry<T> newRegistry () {
		return new RedNamespaceRegistry<T>();
	}

	@Override
	public ID ROOT () {
		return new RedAssetID();
	}

	@Override
	public ID newID (final Collection<String> list) {
		return new RedAssetID(JUtils.newRelativePath(list));
	}

}
