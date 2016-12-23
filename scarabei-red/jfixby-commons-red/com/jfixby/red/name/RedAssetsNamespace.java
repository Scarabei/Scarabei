
package com.jfixby.red.name;

import com.jfixby.cmns.api.assets.AssetsNamespaceComponent;
import com.jfixby.cmns.api.assets.ID;
import com.jfixby.cmns.api.assets.NamespaceRegistry;
import com.jfixby.cmns.api.collections.Collection;
import com.jfixby.cmns.api.util.JUtils;

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
